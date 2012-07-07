import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Long Ho
 * Facebook email: 1051962371@facebook.com
 * Find Sophie puzzle: Sophie can be at multiple places, each 
 * place has a certain probability. Navigating to each place
 * costs time. Thus, in general it looks like a Traveling Salesman
 * problem. There are multiple algorithms to solve it:
 * - DP yields the best results, 0(n^2 2^n), but requires exponential space.
 * It is also pretty hard to implement.
 * - Use Floyd-Warshall to find all-pairs shortest paths, sort those paths using
 * heuristics (increase in expected time) and implementing greedy algorithm on
 * it with pruning. Time complexity is O(n!) with certain optimization 
 * but codebase is much simpler and easy to maintain.
 * 
 * The mathematics behind (example):
 * expTime = 0.1(0) + 0.2(a) + 0.3(a+b) + 0.4(a+b+c) where a, b, c is shortest paths from locations of prob .1, .2, .3 and .4
 * --> expTime = a(0.1+0.2+0.3+0.4) + b(0.3+0.4) + c(0.4)
 * --> expTime = a(1-0.1) + b(1-0.1-0.2) + c(1-0.1-0.2-0.3)
 * So if we keep substracting probability instead of accumulating distance,
 * calculations turn out to be faster 
 * The output is the minimum expected time to find Sophie.
 */
public class sophie {
	
	/**
	 * Using HashMap to associate place to probability would be inefficient
	 * and make it hard to implement adjacency matrix. Thus, it'd be ideal to
	 * convert places to integer id.
	 */
	
	private static double[][] adjMatrix;
	private static double[] probabilities;
	private static boolean[] visited;
	private static double minExpTime;
	private static int placeCount;
	private static ArrayList<Integer> path;
	
	public static void main(String[] args) throws IOException {
		//Not enough arguments
		if (args.length < 1) {
			usage();
		}
		
		System.out.println(findSophie(args[0]));
	}
	
	public static String findSophie(String filename) {
		
		try {
			parseInputFile(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (placeCount == 0) {
			return "-1.00";
		}
		
		floydWarshall();

		//Check if it's possbible to find Sophie
		if (!canFindSophie()) {
			return "-1.00";
		}
		
		
		greedy(0, 0, 1);
		return new DecimalFormat("#0.00").format(minExpTime);
		
	}
	
	/**
	 * Print out the usage of the program and exit
	 */
	private static void usage() {
		System.out.println("java sophie <input file>");
		System.exit(1);
	}
	
	/**
	 * Parse input file for a list of locations and edges connecting them
	 * @param filename The input filename
	 * @throws IOException if input cannot be read
	 */
	private static void parseInputFile(String filename) throws IOException {
		
			File input = new File(filename);
			BufferedReader reader = new BufferedReader(new FileReader(input));
			
			//Determine the number of places Sophie can hide
			placeCount = Integer.parseInt(reader.readLine());
			adjMatrix = new double[placeCount][placeCount];
			probabilities = new double[placeCount];
			visited = new boolean[placeCount];
			minExpTime = Double.POSITIVE_INFINITY;
			path = new ArrayList<Integer>();
			
			
			//Initiate all values in matrix to infinity instead of 0 as default
			for (int i = 0; i < placeCount; i++) 
				for (int j = 0; j < placeCount; j++) 
					adjMatrix[i][j] = Double.POSITIVE_INFINITY;
			
			
			//Read in all the probabilities associated with places, also mark them as not visited
			HashMap<String, Integer> places = new HashMap<String, Integer>();
			for (int i = 0; i < placeCount; i++) {
				String[] lineArray = reader.readLine().split("[ \t]+");
				places.put(lineArray[0], i);
				probabilities[i] = new Double(lineArray[1]);
				visited[i] = false;
				
			}
			
			//Read in all the edges and time cost of traveling among them
			int routeCount = Integer.parseInt(reader.readLine());
			for (int i = 0; i < routeCount; i++) {
				String[] routeArray = reader.readLine().split("[ \t]+");
				int src = places.get(routeArray[0]);
				int dest = places.get(routeArray[1]);
				adjMatrix[src][dest] = adjMatrix[dest][src] = new Double(routeArray[2]);
				
			}
			
			reader.close();
			
		
	}
	
	/**
	 * Floyd-Warshall algorithm implementation, source: Algorithms - Dasgupta, Vazirani
	 * and Papadimitriou. This is faster than repeatedly calling Dijsktra
	 * since loops are tight, thus optimizing the compiler.
	 * A few micro-optimizations:
	 * - Use conditions instead of Math.min
	 * - Take use of symmetrical matrix
	 * - Store local variable to avoid reinitiating;
	 */
	private static void floydWarshall() {
		double value;
		for (int k = 0; k < placeCount; k++)
			for (int i = 0; i < placeCount; i++) {
				for (int j = 0; j < i; j++) {
					value = adjMatrix[i][k] + adjMatrix[k][j];
					if (value < adjMatrix[i][j])
						adjMatrix[i][j] = adjMatrix[j][i] = value;
				}
			}
	}
	
	
	
	
	
	/**
	 * Check if there's any unconnected place. If there is, check
	 * if the probability of that finding Sophie at that place is 0% or 100%.
	 * If it is not, the graph is not connected and Sophie cannot be found
	 * @return true if it's possible to find Sophie, false otherwise.
	 */
	private static boolean canFindSophie() {
		for (int i = 0; i < placeCount; i++) {
			for (int j = 0; j < i; j++)
				if (adjMatrix[i][j] == Double.POSITIVE_INFINITY)
					if (probabilities[i] != 0 && probabilities[i] != 1 && 
						probabilities[j] != 0 && probabilities[j] != 1) {
						return false;
				}
		}
		return true;
	}
	
	/**
	 * Sort all edges and take the greedy path,
	 * also keep track of the best time. The path also stops if value
	 * is already larger than minExpTime. On average it might cut down
	 * a lot of unnecessary path but the worst case is the same.
	 * @param place the id of the place Sophie can hide
	 * @param value the current expected value
	 * @param prob the remaining probability of Sophie at that place
	 */
	private static void greedy(int place, double value, double prob) {
		visited[place] = true;
		path.add(place);
		
		prob -= probabilities[place];
		
			
		Queue<Node> queue = initQueue(place, value, prob);
		boolean done = queue.size() == 0;
		 
		while (queue.size() > 0) {
			Node next = queue.poll();
			if (next.time < minExpTime) {
				greedy(next.id, next.time, prob);
			}
			
		}

		visited[place] = false;
		
		if (done) {
			
			if (value < minExpTime) {
				minExpTime = value;
			}
		}

		path.remove((Integer)place);
		
		
	}
	
    /**
     * Put all paths into priorityqueue (automatically sorted)
     * @param place starting location
     * @param value current expected time
     * @param prob current remaining probability
     * @returns a queue of unvisited nodes, sorted based on increase in
     * expected time
     */
	private static Queue<Node> initQueue(int place, double value, double prob) {
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		double newValue;
		for (int i = 1; i < placeCount; i++) {
			if (!visited[i] && probabilities[i] != 0) {
				newValue = value + prob * adjMatrix[place][i];
				queue.add(new Node(i, newValue));
			}
		}
		return queue;
	}
	
}
