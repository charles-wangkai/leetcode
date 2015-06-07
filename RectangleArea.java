public class RectangleArea {
	public int computeArea(int A, int B, int C, int D, int E, int F, int G,
			int H) {
		return (int) (getArea(A, B, C, D) + getArea(E, F, G, H) - getArea(
				Math.max(A, E), Math.max(B, F), Math.min(C, G), Math.min(D, H)));
	}

	long getArea(int x0, int y0, int x1, int y1) {
		return Math.max(x1 - x0, 0L) * Math.max(y1 - y0, 0L);
	}
}
