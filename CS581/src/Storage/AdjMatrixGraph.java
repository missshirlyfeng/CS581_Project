package Storage;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AdjMatrixGraph {
	private int V;
	private int E;
	private float[][] adj;

	// empty graph with V vertices
	public AdjMatrixGraph(int V) {
		if (V < 0)
			throw new RuntimeException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		this.adj = new float[V][V];
		for (int i = 0; i < V; ++i) {
			for (int j = 0; j < V; ++j) {
				this.adj[i][j] = 0;
			}
		}
	}

	// number of vertices and edges
	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	// add undirected edge v-w
	public void addEdge(int v, int w, float weight) {
		if (adj[v][w] == 0f)
			E++;
		adj[v][w] = weight;
		adj[w][v] = weight;
	}

	// does the graph contain the edge v-w?
	public boolean contains(int v, int w) {
		boolean result = false;
		if (adj[v][w] != 0f) {
			result = true;
		}
		return result;
	}

	// return list of neighbors of v
	public Iterable<Integer> adj(int v) {
		return new AdjIterator(v);
	}

	// support iteration over graph vertices
	private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
		int v, w = 0;

		AdjIterator(int v) {
			this.v = v;
		}

		public Iterator<Integer> iterator() {
			return this;
		}

		public boolean hasNext() {
			while (w < V) {
				if (adj[v][w] != 0f)
					return true;
				w++;
			}
			return false;
		}

		public Integer next() {
			if (hasNext()) {
				return w++;
			} else {
				throw new NoSuchElementException();
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	// string representation of Graph - takes quadratic time
	public void PrintNodesRelation() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			int tmpv = v + 1;
			s.append(tmpv + ": ");
			for (int w : adj(v)) {
				int tmpw = w + 1;
				s.append(tmpw + " ");
			}
			s.append(NEWLINE);
		}
		System.out.print(s.toString());
	}

	// string representation of Graph - takes quadratic time
	public void PrintWeightMatrix() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			int tmpv = v + 1;
			s.append(tmpv + ": ");
			for (int w : adj(v)) {
				int tmpw = w + 1;
				s.append(tmpw + "(");
				s.append(adj[v][w] + ") ");
			}
			s.append(NEWLINE);
		}
		System.out.print(s.toString());
	}

}
