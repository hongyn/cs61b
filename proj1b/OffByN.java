public class OffByN implements CharacterComparator {
    public int offByNumber;

    public OffByN(int N) {
        offByNumber = N;
    }

    /** Return true for characters that are off by N. */
    public boolean equalChars(char x, char y) {
        if (x - y == 5 || y - x == 5)
            return true;
        return false;
    }
}
