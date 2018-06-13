package queue;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.InputMismatchException;
/** Copied**/
public class ChamberOfSecrets {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int n = in.readInt();
		int x = in.readInt();
		CircularQueue spiders = new CircularQueue(n);
		CircularQueue spiderIndex = new CircularQueue(n);
		for (int i = 0; i < n; i++) {
			spiders.enQueue(in.readInt());
			spiderIndex.enQueue(i + 1);
		}
		for (int i = 0; i < x; i++) {
			int max = -1, maxIndex = 0, j;
			int arr[] = new int[x];
			int indexArr[] = new int[x];

			for (j = 0; j < x; j++) {
				int val = spiders.deQueue();
				int iVal = spiderIndex.deQueue();
				arr[j] = val;
				indexArr[j] = iVal;

				if (val == -1)
					break;

				if (val > max) {
					max = val;
					maxIndex = iVal;
				}
			}
			out.print(maxIndex + " ");
			for (int k = 0; k < j; k++) {
				if (indexArr[k] != maxIndex) {
					spiders.enQueue(arr[k] > 0 ? arr[k] - 1 : 0);
					spiderIndex.enQueue(indexArr[k]);
				}
			}
		}
		out.close();
	}

	static class CircularQueue {
		int[] arr;
		int front, rear, size, count;

		CircularQueue(int size) {
			this.front = 0;
			this.rear = 0;
			this.size = size;
			arr = new int[size];
		}

		public boolean isEmpty() {
			return (count == 0);
		}

		public void enQueue(int value) {
			if (size == count) {
				return;
			}
			arr[rear] = value;
			rear = (rear + 1) % size;
			count++;
		}

		public int deQueue() {
			if (isEmpty()) {
				return -1;
			}
			int val = arr[front];
			arr[front] = -1;
			front = (front + 1) % size;
			count--;
			return val;
		}

		public int front() {
			return arr[front];
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
