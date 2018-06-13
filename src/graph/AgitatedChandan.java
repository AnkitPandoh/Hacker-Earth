package graph;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class AgitatedChandan {

	static long maxDis;

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.readInt();
		while (t-- > 0) {
			int n = in.readInt();
			ArrayList<Node> graph[] = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				graph[i] = new ArrayList<Node>();
			}
			for (int i = 0; i < n - 1; i++) {
				int x = in.readInt();
				int y = in.readInt();
				int w = in.readInt();
				graph[x].add(new Node(y, w));
				graph[y].add(new Node(x, w));
			}
			if (n == 0 || n == 1) {
				out.printLine("0 0");
			} else {
				int lastVisited = bfs(graph, 1, n);
				bfs(graph, lastVisited, n);
				int cost = 0;
				if (maxDis > 100)
					cost = 100;
				if (maxDis > 1000)
					cost = 1000;
				if (maxDis > 10000)
					cost = 10000;
				out.printLine(cost + " " + maxDis);
			}
		}
		out.close();
	}

	static int bfs(ArrayList<Node>[] graph, int start, int n) {
		Queue<Integer> qu = new LinkedList<Integer>();
		boolean visited[] = new boolean[n + 1];
		long dis[] = new long[n + 1];
		qu.add(start);
		visited[start] = true;
		int lastVisitedNode = 0;
		maxDis = 0;
		while (!qu.isEmpty()) {
			start = qu.poll();
			ListIterator<Node> node = graph[start].listIterator();
			while (node.hasNext()) {
				Node k = node.next();
				if (!visited[k.value]) {
					visited[k.value] = true;
					qu.add(k.value);
					dis[k.value] += dis[start] + k.distance;
					if (dis[k.value] > maxDis) {
						maxDis = dis[k.value];
						lastVisitedNode = k.value;
					}
				}
			}
		}
		return lastVisitedNode;
	}

	static class Node {
		int value;
		int distance;

		Node(int value, int distance) {
			this.value = value;
			this.distance = distance;
		}
	}

	static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1)
				throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public String readLine() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isEndOfLine(c));
			return res.toString();
		}

		public String readString() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public long readLong() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public boolean isEndOfLine(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}
	}

	static class OutputWriter {
		private final PrintWriter writer;

		public OutputWriter(OutputStream outputStream) {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					outputStream)));
		}

		public OutputWriter(Writer writer) {
			this.writer = new PrintWriter(writer);
		}

		public void print(Object... objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0)
					writer.print(' ');
				writer.print(objects[i]);
			}
		}

		public void printLine(Object... objects) {
			print(objects);
			writer.println();
		}

		public void close() {
			writer.close();
		}
	}
}
