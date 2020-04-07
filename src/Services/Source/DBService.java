package Services.Source;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;


import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Models.Source.UserDetails;
import Models.Source.HistoryItem;
import org.bson.types.ObjectId;
import Services.Interface.IDBService;

public class DBService implements IDBService {

	private  final String DB_NAME = "InsulinApp"; //Database
	private  final String USERS_COL = "users"; //Table
	private  final String HISTORY_COL = "history";//Table
	private  final MongoClientURI connectionUri = new MongoClientURI(
			"mongodb://root:qwe123qwe@cluster0-shard-00-00-nxrrh.mongodb.net:27017," +
			"cluster0-shard-00-01-nxrrh.mongodb.net:27017," +
					"cluster0-shard-00-02-nxrrh.mongodb.net:27017" +
					"/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collectionUser;
	private MongoCollection<Document> collectionHistory;

	private void openDataBase(){
		mongoClient = new MongoClient(connectionUri);
		database = mongoClient.getDatabase(DB_NAME);
		collectionUser = database.getCollection(USERS_COL);
		collectionHistory = database.getCollection(HISTORY_COL);
	}

	private void closeDataBsae(){
		mongoClient.close();
	}

	public String getEmail(UserDetails userDetails) {
		openDataBase();
		FindIterable<Document> docs = collectionUser
				.find(and(eq("email", userDetails.getEmail()), eq("password", userDetails.getPass())));
		Document user = docs.first();
		closeDataBsae();
		//return user.getObjectId("email").toString();
		return userDetails.getEmail();
	}

	public List<HistoryItem> getUserHistory(String userId) {
		openDataBase();
		FindIterable<Document> docs = collectionHistory.find(eq("userId", userId));
		List<HistoryItem> history = new LinkedList<HistoryItem>();
		for (Document doc : docs) {
			history.add(new HistoryItem(doc.getString("BloodSugar"), doc.getString("CarbsAmount"),
					doc.getString("InsulineLevel")));
		}
		closeDataBsae();
		return history;
	}

	public void addHistoryItem(String userId, HistoryItem item) {
		openDataBase();
		collectionHistory.insertOne(new Document("date", item.getDate()).append("BloodSugar", item.getBloodSugar())
				.append("CarbsAmount", item.getCarbsAmount()).append("InsulineLevel", item.getInsulinLevel())
				.append("userId", userId));
		closeDataBsae();
	}

	public boolean isUserExist(String username) {
		openDataBase();
		long count = collectionHistory.count(eq("userId", username));
		closeDataBsae();
		return count > 0;
	}

	public boolean isUserExist(UserDetails userDetails) {
		openDataBase();
		long count = collectionUser
				.count(and(eq("email", userDetails.getEmail()), eq("password", userDetails.getPass())));
		closeDataBsae();
		return count == 1;
	}

	public boolean addUser(UserDetails userDetails) {
		if (isUserExist(userDetails)) {
			return false;
		}
		openDataBase();
		collectionUser.insertOne(new Document("email", userDetails.getEmail()).append("password", userDetails.getPass()).
				append("firstname",userDetails.getFirstName()).append("lastname",userDetails.getLastName()).
				append("phone",userDetails.getPhone()));
		closeDataBsae();
		return true;
	}

	public String getName(String userId) {
		openDataBase();
		FindIterable<Document> docs = collectionUser.find(eq("email", userId));
		Document user = docs.first();
		closeDataBsae();
		return user.getString("firstname").toString()+" "+user.getString("lastname").toString();
	}
}
