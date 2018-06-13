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
import java.util.List;
import java.util.Queue;

public class BarHopping {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int n = in.readInt();
		int[] arr = new int[n - 1];
		for (int i = 0; i < n - 1; i++) {
			arr[i] = in.readInt();
		}
		Node[] nodes = update(n, arr);
		int op = in.readInt();
		while (op-- > 0) {
			String type = in.readString();
			if ("Q".equals(type)) {
				out.printLine(bfs(nodes[in.readInt()], nodes));
			} else {
				nodes = update(n, arr);
			}
		}
		out.close();
	}

	static int bfs(Node root, Node[] nodes) {
		Queue<Node> qu = new LinkedList<Node>();
		qu.add(root);
		int count = 0;
		while (!qu.isEmpty()) {
			count++;
			Node v = qu.poll();
			if (v != null) {
				for (Node node : v.neighbours) {
					qu.add(nodes[node.value]);
				}
			}
		}
		return count;
	}

	static Node[] update(int n, int[] arr) {
		Node[] nodes = new Node[n + 1];
		for (int i = 1; i <= n; i++) {
			if (nodes[i] == null) {
				nodes[i] = new Node(i);
			}
			if (i <= n - 1) {
				nodes[i + 1] = new Node(i + 1);
				if (arr[i - 1] == 1) {
					nodes[i].neighbours.add(nodes[i + 1]);
				} else {
					nodes[i + 1].neighbours.add(nodes[i]);
				}
				if (arr[i - 1] == 1) {
					arr[i - 1] = 0;
				} else {
					arr[i - 1] = 1;
				}
			}
		}
		return nodes;
	}

	static class Node {
		int value;
		List<Node> neighbours;

		Node(int value) {
			this.value = value;
			this.neighbours = new ArrayList<Node>();
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
