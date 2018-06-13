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
import java.util.ListIterator;
import java.util.Stack;

public class OliverAndBattle {

	static boolean[] visited;

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.readInt();
		while (t-- > 0) {
			int n = in.readInt();
			int m = in.readInt();
			int[][] troops = new int[n][m];
			int s = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					troops[i][j] = in.readInt();
					if (troops[i][j] == 1) {
						troops[i][j] = ++s;
					}
				}
			}
			if(s == n*m){
				out.printLine(1 + " " + n*m);
			}else{
				ArrayList<Node> graph[] = new ArrayList[s + 1];
				for (int i = 1; i <= s; i++) {
					graph[i] = new ArrayList<Node>();
				}
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						int v = troops[i][j];
						if (v != 0) {
							// right
							if (j + 1 < m && troops[i][j + 1] != 0) {
								graph[v].add(new Node(troops[i][j + 1]));
							}
							// left
							if (j - 1 >= 0 && troops[i][j - 1] != 0) {
								graph[v].add(new Node(troops[i][j - 1]));
							}
							// top
							if (i - 1 >= 0 && troops[i - 1][j] != 0) {
								graph[v].add(new Node(troops[i - 1][j]));
							}
							// bottom
							if (i + 1 < n && troops[i + 1][j] != 0) {
								graph[v].add(new Node(troops[i + 1][j]));
							}
							// Diagonal Right Up
							if (i - 1 >= 0 && j + 1 < m
									&& troops[i - 1][j + 1] != 0) {
								graph[v].add(new Node(troops[i - 1][j + 1]));
							}
							// Diagonal Right Down
							if (i + 1 < n && j + 1 < m && troops[i + 1][j + 1] != 0) {
								graph[v].add(new Node(troops[i + 1][j + 1]));
							}
							// Diagonal Left Up
							if (i - 1 >= 0 && j - 1 >= 0
									&& troops[i - 1][j - 1] != 0) {
								graph[v].add(new Node(troops[i - 1][j - 1]));
							}
							// Diagonal Left down
							if (i + 1 < n && j - 1 >= 0
									&& troops[i + 1][j - 1] != 0) {
								graph[v].add(new Node(troops[i + 1][j - 1]));
							}
						}
					}
				}
				visited = new boolean[graph.length];
				int max = 0;
				int numTroops = 0;
				for(int i=1;i<=s;i++){
					if(!visited[i]){
						int total = dfs(graph,i);
						numTroops++;
						max = Math.max(total, max);
					}
				}
				out.printLine(numTroops + " " + max);
			}
		}
		out.close();
	}

	static int dfs(ArrayList<Node> graph[], int start) {
		Stack<Integer> st = new Stack<Integer>();
		st.push(start);
		visited[start] = true;
		int totalNodes = 1;
		while (!st.isEmpty()) {
			int v = st.pop();
			ListIterator<Node> itr = graph[v].listIterator();
			while (itr.hasNext()) {
				int k = itr.next().value;
				if (!visited[k]) {
					st.push(k);
					visited[k] = true;
					totalNodes++;
				}
			}
		}
		return totalNodes;
	}

	static class Node {
		int value;

		Node(int value) {
			this.value = value;
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
