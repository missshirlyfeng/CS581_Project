package GraphAlgorithm;

import java.util.ArrayList;

import Common.Query;
import Storage.AdjMatrixGraph;

public class Graph extends AdjMatrixGraph{
	
	public Graph(int V)
	{
		super(V);
	}
	
	public void UpdateFromDB()
	{
		Query qry = new Query();
		int node_id = 7001;
		for (int i = 0; i < super.V(); ++i) {
			int node1 = node_id + i;
			// node i has no adjacent node. Continue to update the next node's
			// adjNodes
			if (qry.GetAdjNodes(node1).size() == 0) {
				continue;
			}
			for (int j = 0; j < super.V(); ++j) {

				// record the weight of from the current node to the adjacent
				// one
				ArrayList<Integer> adjNodes = qry.GetAdjNodes(node1);
				for (Integer node2 : adjNodes) {
					super.addEdge(i, node2 - node_id, Float.valueOf(qry
							.getShortestBlockStringField("distance", node1,
									node2)));
				}
			}
		}
	}

	@Override
	public void PrintNodesRelation() {
		// TODO Auto-generated method stub
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		s.append(super.V() + " " + super.E() + NEWLINE);
		for (int v = 0; v < super.V(); v++) {
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

	public void PrintWeightMatrix() {
		// TODO Auto-generated method stub
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		s.append(super.V() + " " + super.E() + NEWLINE);
		for (int v = 0; v < super.V(); v++) {
			int tmpv = v + 1;
			s.append(tmpv + ": ");
			for (int w : adj(v)) {
				int tmpw = w + 1;
				s.append(tmpw + "(");
				s.append(super.getCell(v, w) + ") ");
			}
			s.append(NEWLINE);
		}
		System.out.print(s.toString());
	}
	
}
