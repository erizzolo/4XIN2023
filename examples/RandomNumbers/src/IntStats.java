public class IntStats {

    private final int[] bitCount;
    private final int[] frequencies;
    private final int[] ranges;

    public IntStats(int[] ranges) {
        this.ranges = ranges;
        frequencies = new int[ranges.length];
        bitCount = new int[32];
    }

    public void update(int value) {
        for (int i = 0, m = 1; i < bitCount.length; ++i, m <<= 1) {
            if ((m & value) != 0) {
                bitCount[i]++;
            }
        }
        int r = 0;
        while (value > ranges[r])
            ++r;
        frequencies[r]++;
    }

}
