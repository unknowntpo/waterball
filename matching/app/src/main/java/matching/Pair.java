package matching;

public class Pair {
    private Individual left;
    private Individual right;

    @Override
    public String toString() {
        return "Pair [left=" + left + ", right=" + right + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((left == null) ? 0 : left.hashCode());
        result = prime * result + ((right == null) ? 0 : right.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (left == null || right == null)
            return false;

        Pair other = (Pair) obj;
        return left.equals(other.left) && right.equals(other.right);
    }

    public Pair(Individual left, Individual right) {
        // left should be the one which has smaller id
        if (left.getId() > right.getId()) {
            this.left = right;
            this.right = left;
            return;
        }
        this.left = left;
        this.right = right;
    }
}
