package Common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBControl.dbTier;

public class Query {

	dbTier db;

	public Query() {
		db = new dbTier();
	}

	public ArrayList<Integer> GetAdjNodes(int node_id) {
		StringBuilder sql = new StringBuilder();
		String outFieldName = "node_id_2";
		String inputFieldName = "node_id_1";
		String tableName = "sfpark_edge";
		ArrayList<Integer> adjNodes = new ArrayList<Integer>();
		sql.append("select ");
		sql.append(outFieldName);
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(inputFieldName);
		sql.append("=");
		sql.append(node_id);
		adjNodes = GetIntArrayList(sql, outFieldName);
		return adjNodes;
	}

	protected ResultSet GetShortestBlockInfo(int node_id1, int node_id2) {

		ResultSet rs = null;

		String inputFieldName1 = "node_id_1";
		String inputFieldName2 = "node_id_2";
		String tableName = "sfpark_edge";

		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(inputFieldName1);
		sql.append("=");
		sql.append(node_id1);
		sql.append(" and ");
		sql.append(inputFieldName2);
		sql.append("=");
		sql.append(node_id2);
		sql.append(" order by distance asc limit 1");

		rs = db.ExecuteNonScalarQuery(sql.toString());
		return rs;
	}

	public String getShortestBlockStringField(String fieldName, int node1,
			int node2) {
		String str = "";
		ResultSet rs = GetShortestBlockInfo(node1, node2);

		try {
			while (rs.next()) {
				str = rs.getString(fieldName);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public int getShortestBlockIntField(String fieldName, int node1,
			int node2) {
		int result=0;
		ResultSet rs = GetShortestBlockInfo(node1, node2);

		try {
			while (rs.next()) {
				result = rs.getInt(fieldName);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private ArrayList<Integer> GetIntArrayList(StringBuilder sql,
			String outFieldName) {
		ArrayList<Integer> intArrayList = new ArrayList<Integer>();
		ResultSet rs = db.ExecuteNonScalarQuery(sql.toString());
		try {
			while (rs.next()) {
				intArrayList.add(rs.getInt(outFieldName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return intArrayList;

	}

	// private ResultSet GetSetbyCondition(String tableName,ArrayList<String>
	// fields)
	// {
	// ResultSet rs= null;
	// StringBuilder sql = new StringBuilder();
	// sql.append("select * from ");
	// sql.append(tableName);
	// sql.append(" where ");
	// for(String it:fields)
	// {
	// sql.append(it);
	// sql.append("=")
	// }
	// }

}
