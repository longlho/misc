import java.util.*;

/**
 * Represents a sale terminal.
 */
public class Terminal {

    private Map<String, Integer> cart = new HashMap<String, Integer>();
    private Map<String, TreeSet<PriceUnit>> prices = new HashMap<String, TreeSet<PriceUnit>>();

    /**
     * Empty constructor.
     */
    public Terminal() {}

    /**
     * Set the prices based on a CSV since it's a common tabular extract format.
     * @param pricesCsv Prices in CSV, 1st column is product name, 2nd is volume, 3rd is price.
     * @throws Exception if pricesCsv has invalid format, or not properly encoded to avoid comma
     * conflicts.
     */
    public void setPricing(String pricesCsv) throws Exception {
        String[] prices = pricesCsv.trim().split("\\n");
        String[] priceEntry;
        for (String entry : prices) {
            priceEntry = entry.split(",");
            //If improperly format, it'll choke.
            setPricing(priceEntry[0], Integer.parseInt(priceEntry[1]), Float.parseFloat(priceEntry[2]));
        }
    }

    /**
     * Incrementally add price units to the data model.
     * @param product product name.
     * @param volume volume.
     * @param price price corresponding to the volume.
     */
    private void setPricing(String product, int volume, float price) {
        if (!prices.containsKey(product)) {
            prices.put(product, new TreeSet<PriceUnit>());
        }
        Set<PriceUnit> productPrices = prices.get(product);
        productPrices.add(new PriceUnit(volume, price));
    }

    /**
     * Scan a product into the cart.
     * @param product product name.
     */
    public void scan(String product) {
        if (!cart.containsKey(product)) {
            cart.put(product, 0);
        }
        cart.put(product, cart.get(product) + 1);
    }

    /**
     * Get the total price of the cart.
     * @return total price of the products in the cart, formatted to 2 decimal points.
     */
    public String getTotal() {
        float total = 0;
        for (Map.Entry<String, Integer> e : cart.entrySet()) {
            total += calculate(e.getKey(), e.getValue());
        }
        return '$' + String.format("%.2f", total);
    }

    /**
     * Calculate prices of a product based on its volume.
     * @param product product name.
     * @param volume volume cart.
     * @return total price.
     * @throws IllegalArgumentException if product doesn't have pricing in the system.
     */
    private float calculate(String product, int volume) throws IllegalArgumentException {
        if (!prices.containsKey(product)) {
            throw new IllegalArgumentException(product + " does not have pricing in the system");
        }
        float total = 0;
        int remainder = volume;
        Set<PriceUnit> model = prices.get(product);
        //Loop through price units and calculate bulk prices first, then unit prices.
        for (PriceUnit p : model) {
            total += p.price * (remainder/p.volume);
            remainder = volume % p.volume;
        }
        return total;
    }

    /**
     * Simple struct that stores prices and volume.
     * Sorted by volume so that we calculate bulk items 1st.
     */
    private class PriceUnit implements Comparable<PriceUnit> {

        //The assumption here is volume won't pass Integer.MAX_VALUE.
        int volume;
        float price;

        PriceUnit(int volume, float price) {
            this.volume = volume;
            this.price = price;
        }

        @Override
        public int compareTo(PriceUnit o) {
            if (o.volume == volume) {
                return 0;
            }
            return o.volume > volume ? 1 : -1;
        }
    }
}
