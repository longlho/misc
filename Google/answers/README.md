http://placementsindia.blogspot.com/2007/12/solutions-to-few-google-top-interview.html

1. Assume `rand5()` gives u a random number from 1 to 5. Then `rand7()` would be:

		def rand7():
			sum = 0
			for i in range(3):
				sum += rand5() - 1
			return sum/2+1

		public double rand7() {
			double sum = 0;
			for (int i = 0; i < 3; i++) {
				sum += rand5() - 1;
			}
		return sum/2 + 1;

2. `0xDEADBEEF` marks uninitialized memory value.

3. Depth First Search:

   		#Recursion
   		def dfs(root):
			if root is not visited:
				mark root as visited
			for child in root.children:
				if child is not visited:
				dfs(child)

   		#Iterative, might not work
   		def dfs(root, path):
			if root is not visited:
				mark root as visited
				add root to path
			while path is not empty:
				root = path[-1]
				if root has no children:
					path.pop()
				else:
					for child in root's children:
					if child is not visited:
						mark child as visited
						add child to path
						break

4. Card games:
	- Card class w/ # and suit
	- Deck of cards w/ a stack of cards and shuffle method
	- Game class controls the rules

5. Besides the question, send your public key. Bob can use the public key to encrypt the info and send it back to you. Once received, you can use your private key to decrypt the message.

6. Cookies are passed unchanged from web browser to server through an HTTP request. Cookies are basically pairs of `key=value` text string. Once there's an update the server will send back a new `key=value` piece to update the cookie.

7. `[a-zA-Z0-9-_]+@[a-zA-Z0-9-_]+\.[a-zA-Z]{2,4}+`

8. Code:

		common(a, b):
			commonChars = []
			if len(a) != 0 and len(b) != 0:
				table = {}
				for c in b:
					table[c] = 1
				for c in a:
					if table[c] != None:
						commonChars.append(c)
		return commonChars

		common(a, b):
			commonChars = []
			if len(a) != 0 and len(b) != 0:
				for c in a:
					if c in b:
						commonChars.append(c)
			return commonChars



9. Car table w/ columns: car id, car model, info, rent by (Customer id), rate. Customer table w/ columns: id, first name, last name, ssn

10. `ACK` and `NAK`, have to look at Networks again

11. - `final` is a keyword for class, method, variable to avoid changes. final class cannot be subclassed, final method cannot be override, final variable cannot be changed
	- `finalize()` s a method that gets called right before class object is destroyed or collected by garbage
	- `finally` in try/catch. Any code within finally will be executed after try/catch is finished.

12. - Multithreaded programming: allows multiple threads to exist in the same process. They share resources but execute independently so codes don't have to be run sequentially while they can work in parallel.
	- deadlock: while 2 or more threads are waiting for each other to release a resource. None of them can move on since they each hold a piece of lock and both need all pieces to move on. 
	Example: threads transferring money. T1 is transferring from acc A to B while T2 is transferring from acc B to A. T1 has locked A and needs lock for B while T2 has locked B and needs lock for A. They're deadlocked.

13. Code:
	
		letterMap = {'A': 1, 'B': 2, 'C': 3… 'Z': 25}

		def findValue(column):
			value = 0
			length = column.length
			for i in range(length):
				value += math.power(len(letterMap),i)+letterMap[column[i]]
			return value

14. No idea


15. Code:

		def dfs (root):
			if root not in visited:
				mark root as visited
			for child in root.children:
				dfs(child)
	

		def bfs(root, queue):
			if root is not visited:
				mark root as visited
			for child in root.children:
				if child is not visited:
					mark child as visited
					enqueue child to queue
				bfs(queue.dequeue(), queue)

	Assume branching factor b, depth level d
	- dfs: time complexity O(b^d), space complexity O(E+V)
	- bfs: same for finite

16. Code:

		def findMin(circList):
			start = a random element in circList
			while start.next > start:
				start = start.next
			return start

17. Stack is LIFO data structure. Every a method is called, it gets pushed into the stack and assign a few blocks in the stack to work with. Once the method is done its popped out of the stack and return to the position in the stack where it was called.

18. Difference in scope: local variable can only be used within a method. Global variable is visible within the whole class.

19. If we have the array of 1 mil int already, quicksort can do the job.

		quicksort(array)
			pivot = random element from array
			start = 0
			for i in range(len(array)):
				if array[i] <= array[pivot]:
					temp = array[start]
					array[start] = array[i]
					array[i] = temp
					start += 1
			temp = array[start]
			array[start] = array[pivot]
			array[pivot] = temp
			quicksort(array[:start])
			quicksort(array[start+1:])

	O/w use merge sort

		insertion_sort(array):
			for i in range(len(array)):
				value = array[i]
				j = i - 1
				while j >= 0 and array[j] > value:
					array[j + 1] = a[j]
					j--
				array[j+1] = value
			

		mergesort(array):
			if len(array) < 7:
				return insertion_sort(array)
			return merge(mergesort(array[:len(array)/2]),mergesort(array[len(array)/2:]))

		merge(a1, a2):
			if a1[-1] < a2[0]:
				return a1 + a2
			else if a1[0] > a2[-1]:
				return a2 + a1
			result = []
			i = 0
			j = 0
			while i < len(a1) and j < len(a2):
				if a1[i] <= a2[j]:
					result.append(a1[i])
					i++
				else 
					result.append(a2[j])
					j++
			return result + a1[i:] + a2[j:]

20. - static variable is a global variable shared across classes and doesn't need an instance of that class to exist 
	- static method shares the same concept 
	- final variable is a constant that cannot be changed, still need class instance
	- final method cannot be overridden

21. Optimize data structures, utilize try catch

22. No idea

23. Code:

		def reverse(s):
			result = ''
			for i in range(len(s)):
				result += s[len(s)-i-1]
			return result

24. Code:

		def div(a, b):
		    if !(a & b):
			   if b == 0:
				 throw exception
			   return 0
		    if (a & b < 0):
        		return -find_division(a, b)
		    return find_division(a,b)

		def find_division(a, b):
		    a = abs(a)
		    b = abs(b)
		    res = 0
		    while ((b-=a) >= 0):
        		res += 1
		    return res

25. Code:

		def permute(s)
		    if len(s) == 1:
        		return s
		    if len(s) == 2:
        		return s[1]+s[0]
		    result = s
		    for i in range(len(s)):
        		if i > 0:
		            result = s[i] + s[1:i] + s[0] + s[i+1:len(s)]
        		    print result
		        print result[0] + permute(result[1:])
        		result = s

26. No idea

27. Categorize them by color, pattern, long/short, type. Give each shirt a number and in those categories link the key to that number.
	For ex:
	Color table:
		red --> 4
		brown --> 3
		white --> 1
		black --> 2

28.
	Easy: split into 3 3 2
	Weigh the 2 3s, balance, weigh the 2 separately
	Otherwise, split the 3 into 1 1 and same method

29. hmmm, not C

30. No idea
31. Keep track of 2 lists: forward and backward

		def mult(array):
		    length = len(array)
		    forward = copy.deepcopy(array)
		    backward = copy.deepcopy(array)
		    result = []
		    for i in range(length):
		        if i == 0:
		            forward[i] = 1
		            backward[length - i - 1] = 1
		        else:
		            forward[i] = forward[i-1]*array[i-1]
		            backward[length - i - 1] = backward[length - i]*array[length - i]
		    for i in range(length):
		        result.append(forward[i]*backward[i])
		    return result

32. - If we're allowed to iterate through the list and find N. Then as u iterate through u can generate a number and if its less then 1/N add it to the result list
	- If we're not allowed to find N, every time u go through an element, generate a number and keep track of top k numbers.

33. bitmap???? don't understand

34. No idea

35. Same as 31

36. Maintain 2 array, 1 inorder and 1 either post/pre-order

		def inorder(root):
			if root == null:
				return
			else:
				inorder(root.left)
				print root
				inorder(root.right)
		
		def preorder(root):
			if root == null:
				return
			else:
				print root
				inorder(root.left)
				inorder(root.right)
		
		def postorder(root):
			if root == null:
				return
			else:
				inorder(root.left)
				inorder(root.right)
				print root

37. Code:

		def findnthMax(root, limit):
			if limit == 0:
				return root
			cnt = 0
			res = root
			while root.right != null:
				if (cnt >= limit)
					res = root
				root = root.right
				cnt += 1
			if cnt >= limit:
				if root.right.left != None:
					res = root.right.left
			if cnt < limit:
				return findnthMax(root.left, limit - cnt)
			return res

38. Wrong answer:

		def alternate(seq):
		    length = len(seq)
		    if length == 4:
		        return [seq[0], seq[2], seq[1], seq[3]]
		    elif length == 3:
		        return [seq[0], seq[2], seq[1]]
		    oneForth = length/4
		    for i in range(length/2 - oneForth, length/2 + oneForth + 1):
		        temp = seq[i]
		        seq[i] = seq[i + oneForth]
		        seq[i + oneForth] = temp
		    return alternate(seq[:length/2]) + alternate(seq[length/2:])

39. Data stream research by Rutgers???

40. Windowing??

41. 3 lines. The 3 points makes a triangle, says ABC
	- Line 1: midpoint of AB to midpoint AC
	- Line 2: midpoint of AB to midpoint BC
	- Line 3: midpoint of AC to midpoint BC

42. Depth first search. This might not work when there's loop

43. Construct a hashtable of size N, key would be string of length L starting from each position, value will be when it first occurs.
	O(N+M) time but also O(N) space complexity.

44. Code:

		def binaryTreeCheck(root):
			if root == None:
				return True
			if root.left != None:
				if root.left.value >= root.value or not binaryTreeCheck(root.left):
					return False
			if root.right != None:
				if root.right.value < root.value or not binaryTreeCheck(root.right):
					return False
			return True

45. Binary search on the smallest and largest number of the small sorted list. Should take log time. Then we will retrieve a range of blocks the numbers in the list might be in. After that binary search on each of them should solve it.

46. If all companies are unique, total number of ways to merge would be 2^n

47. Use merge sort, while merging the duplicate should be detected. Time complexity `n log n`

48. `HashMap` and `PriorityQueue`

49. A stack should have constant time push and pop. For extract min, we can keep another heap S2. Every time a new element is push into the normal stack, if its smaller than the top element in S2, push it to S2. When pop, if the popped element is the same as top element in S2, pop it from S2 also.

50. Solution 1 (not as good):

		def find_amount(amount):
		    try:
		        return table[amount]
		    except Exception:
		        basic = [1,3,5,7]
		        for i in range(1, amount+1):
		            list = []
		            try:
		                list.append(table[i])
		            except Exception:
		                pass
		            for coin in basic:
		                try:
		                    list.append(table[i-coin]+1)
		                except Exception:
		                    pass
		            table[i] = find_min(list)
		        return table[amount]

	Solution 2:
	
		def coinComb(amount):
		    global comb
		    try: 
		        return comb[amount]
		    except:
		        if amount < 5:
		            least = amount
		        elif amount < 10:
		            least = amount/5 + coinComb(amount%5)
		        elif amount < 25:
		            least = amount/10 + coinComb(amount%10)
		        else:
		            least = amount/25 + coinComb(amount%25)
		        comb[amount] = least
		        return least

51. Code:

		def find_cont_seq(seq):
		    if len(seq) == 1:
		        return seq
		    longest_seqs = [[0], [0]]
		    for i in range(len(seq)):
		        if i == longest_seqs[0][-1] + 1 and seq[i] > seq[longest_seqs[0][-1]]:
		            longest_seqs[0].append(i)
		        if i == longest_seqs[1][-1] + 1 and seq[i] > seq[longest_seqs[1][-1]]:
		            longest_seqs[1].append(i)
		            if len(longest_seqs[1]) >= len(longest_seqs[0]):
		                longest_seqs[0] = longest_seqs[1]
		        elif seq[i] <= seq[longest_seqs[1][-1]]:
		            longest_seqs[1] = [i]
		    result = []
		    for index in longest_seqs[0]:
		        result.append(seq[index])
		    return result
		
		
		def find_seq(seq):
		    if len(seq) == 1:
		        return seq
		    longest_seqs = [[seq[0]], [seq[0]]]
		    for i in range(len(seq)):
		        if seq[i] > seq[longest_seqs[0][-1]]:
		            longest_seqs[0].append(i)
		        if seq[i] > seq[longest_seqs[1][-1]]:
		            longest_seqs[1].append(i)
		            if len(longest_seqs[1]) >= len(longest_seqs[0]):
		                longest_seqs[0] = longest_seqs[1]
		        elif seq[i] <= seq[longest_seqs[1][-1]]:
		            longest_seqs[1] = [i]
		    result = []
		    for index in longest_seqs[0]:
		        result.append(seq[index])
		    return result
		

52. Same as 46.

53. Code:

		def get_middle(head):
			slow_iter = head
			fast_iter = head
			i = 0
			while fast_iter != null:
				fast_iter = fast_iter.next
				if i % 2 == 1:
					slow_iter = slow_iter.next
				i += 1
			return slow_iter

54. Code:

		def compare(root1, root2):
			if root1 == null xor root2 == null:
				return False
			if root1.value != root2.value
				return False
			if not compare(root1.left, root2.left) or not compare(root1.right, root2.right):
				return False
			return True

55. LRU replacement algorithm?

56. Keep increasing the smallest number in the triplet until it becomes larger than the 2nd smallest. We start with `a[0]`, `b[0]`, `c[0]`, once `a[0] > b[0]`, check difference, if smaller then record it. Otherwise keep increasing the new smallest element.

57. No idea

58. Just `XOR` the byte with itself.
	Go through the list and make a hashmap with the key as the word and value as the frequency. After that make a `PriorityQueue` with size m and start pushing the first m, then pop if the priority value is bigger than min on the queue.

59. 4Gb

60. Make a `HashMap` with key is string of length L and start at each character in the long string. Then finding each small string should take constant time.
	Time complexity would be `O(L+M)`, space complexity would be `O(L)`

61. Same as question 37

62. Same as 46

63. As you go through the list, generate a random number from 0 to 1 and keep track of the highest random number, then return the element with the highest number.

64. You should send your public key so that Bob can encode it with the key and send back to you. Then you'll use your private key to decode the phone number

65. Any comparison sort would take `n log n`

66. `n^100`, `2^n`, `n!` and `n^n`

67. Make a class for the triplet that extends Comparable class. Write compareTo method similar to:

		public int compareTo(Triplet t) {
			int difference = 2*(x - t.x) + 3*(y - t.y) + 5*(z - t.z)
			if (difference > 0)
				return 1;
			else if (difference == 0)
				return 0;
			return -1;
		}

	After that make a `PriorityQueue` of size `K` and start pushing/popping larger/smaller value;

68. 3:15
	1 hr is 360/12 = 30 deg
	So the degree is 30 * 15/60 = 30/4 = 7.5 deg

69. Use binary search and keep track of all the indices where the number was found in a heap. Then extract min from the heap.

70. Use mergesort for `LinkedList` to sort the 2 arrays. Then finding common numbers should be easy by merging the 2 linked list together and if a common value is found, copy it to a separate list

71. `HashMap` is unsynchronized and permits null. HashMap doesn't guarantee order of map over time

72. No idea

73. No idea

74. No idea

75. No idea

76. No idea

77. Merge sort since it can sort each small partition and merge them together and output to a big file.

78. Binary Tree Sort, always `N log N`

79. Using any sort of mapping/dictionary.

80. No idea

81. No idea

82. Code:

		def transform(root1, root2):
		   if root1 == None or root2 == None:
			return
		   parent = root1
		   while root1.value != root 2.value:
			parent = root1
			if root1.value < root2.value:
			   root1 = root1.right
			else:
			   root1 = root1.left
		   if parent.value < root1.value:
			parent.right = root1.left
			root1.left = parent
		   else:
			parent.left = root1.right
			root1.right = parent
		   transform(root1.left, root2.left)
		   transform(root1.right, root2.right)

83. Code:

		def findElements(n1, n2):
		   if n1.value == n2.value:
			return []
		   elif n2.value < n1.value:
			return findElements(n2, n1)
		   elif root.value > n1.value and root.value < n2.value:
			return findOneSideElements(root, n1) + findOneSideElements(root, n2)
		   else:
			return findOneSideElements(n1, n2)
		
		def findOneSideElements(n1, n2):
		   start = root
		   result = []
		   while root.value != n1.value:
			if root.value < n1.value:
			   root = root.right
			else:
			   root = root.left
		   while root.value != n2.value:
			if root.value < n1.value:
			   root = root.right
			else:
			   root = root.left
			result.append(root)
		   return result

84. Pick a pivot and start quicksort 1st iteration. If left partition size is more than k, recursively do that until size < k, then do k - size element search on right partition.

85. No idea
