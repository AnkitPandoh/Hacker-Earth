package string;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.InputMismatchException;

public class NameGame {

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int t = in.readInt();
		while (t-- > 0) {
			int n = in.readInt();
			String s = in.readString();
			boolean[] arr = new boolean[150];
			arr[67] = true;
			arr[71] = true;
			arr[73] = true;
			arr[79] = true;
			arr[83] = true;
			arr[89] = true;
			arr[97] = true;
			arr[101] = true;
			arr[103] = true;
			arr[107] = true;
			arr[109] = true;
			arr[113] = true;
			StringBuilder output = new StringBuilder();
			for (int i = 0; i < n; i++) {
				int num = s.charAt(i);
				if(arr[num]){
					output.append((char) num);
				}else{
					if (num < 67) {
						output.append((char) 67);
					} else if (num >= 67 && num <= 71) {
						int x = Math.abs(num - 67);
						int y = Math.abs(num - 71);
						if (x <= y) {
							output.append((char) 67);
						} else {
							output.append((char) 71);
						}
					} else if (num > 71 && num <= 73) {
						int x = Math.abs(num - 71);
						int y = Math.abs(num - 73);
						if (x <= y) {
							output.append((char) 71);
						} else {
							output.append((char) 73);
						}
					} else if (num > 73 && num <= 79) {
						int x = Math.abs(num - 73);
						int y = Math.abs(num - 79);
						if (x <= y) {
							output.append((char) 73);
						} else {
							output.append((char) 79);
						}
					} else if (num > 79 && num <= 83) {
						int x = Math.abs(num - 79);
						int y = Math.abs(num - 83);
						if (x <= y) {
							output.append((char) 79);
						} else {
							output.append((char) 83);
						}
					} else if (num > 83 && num <= 89) {
						int x = Math.abs(num - 83);
						int y = Math.abs(num - 89);
						if (x <= y) {
							output.append((char) 83);
						} else {
							output.append((char) 89);
						}
					} else if (num > 89 && num <= 97) {
						int x = Math.abs(num - 89);
						int y = Math.abs(num - 97);
						if (x <= y) {
							output.append((char) 89);
						} else {
							output.append((char) 97);
						}
					} else if (num > 97 && num <= 101) {
						int x = Math.abs(num - 97);
						int y = Math.abs(num - 101);
						if (x <= y) {
							output.append((char) 97);
						} else {
							output.append((char) 101);
						}
					} else if (num > 101 && num <= 103) {
						int x = Math.abs(num - 101);
						int y = Math.abs(num - 103);
						if (x <= y) {
							output.append((char) 101);
						} else {
							output.append((char) 103);
						}
					} else if (num > 103 && num <= 107) {
						int x = Math.abs(num - 103);
						int y = Math.abs(num - 107);
						if (x <= y) {
							output.append((char) 103);
						} else {
							output.append((char) 107);
						}
					} else if (num > 107 && num <= 109) {
						int x = Math.abs(num - 107);
						int y = Math.abs(num - 109);
						if (x <= y) {
							output.append((char) 107);
						} else {
							output.append((char) 109);
						}
					} else if (num > 109 && num <= 113) {
						int x = Math.abs(num - 109);
						int y = Math.abs(num - 113);
						if (x <= y) {
							output.append((char) 109);
						} else {
							output.append((char) 113);
						}
					} else if (num > 113) {
						output.append((char) 113);
					}
				}
			}
			out.printLine(output.toString());
		}
		out.close();
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
