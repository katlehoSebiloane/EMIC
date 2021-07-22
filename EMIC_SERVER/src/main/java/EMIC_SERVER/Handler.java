package EMIC_SERVER;

import java.io.*;

import java.net.Socket;
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.ServerAddress;

import javafx.application.Platform;
import org.bson.Document;

import java.util.*;

import com.mongodb.Block;

import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;

public class Handler implements Runnable{
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private File logs = null;
    private FileInputStream fis = null;
    private Scanner sc = null;
    private FileWriter logWriter = null;
    private Socket s;
    private String ID;
    private int counter=0;
    MongoClient mongoClient = MongoClients.create();
    MongoDatabase database = mongoClient.getDatabase("EMIC");
    MongoCollection<Document> collection;


    public Handler(Socket socket) throws IOException {
        this.s = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        printWriter = new PrintWriter(s.getOutputStream(),true);
    }

    @Override
    public void run() {

        while (true) {
            try {
                if(bufferedReader != null){
                    String Command = bufferedReader.readLine();
                    String Type = Command.split("[|]")[0];
                    String collectionName;
                    if(Command.contains("MINDRAY_D-SERIES^00A037009A00690C")){
                        collectionName = "D3_TEST_ROUND_TWO";
                        collection = database.getCollection(collectionName);
                        System.out.println("Collection name set....");
                    }

                    if(Command.contains("MINDRAY_D-SERIES^00A03700990018A2")){
                        collectionName = "D6_TEST_ROUND_TWO";
                        collection = database.getCollection(collectionName);
                        System.out.println("Collection name set....");
                    }

                    if (Command.equals("SHOW")) {
                        FindIterable findIterable = collection.find();
                        Iterator it = findIterable.iterator();
                        int i =0;
                        while(it.hasNext()){
                            i++;
                            System.out.println(i);
                            Document reading = (Document)it.next();
                            System.out.println(reading);
                        }
                        printWriter.flush();

                    }



                    if (Command.contains("CONNECT")) {
                        System.out.println("NEW CONNECTION DETECTED");
                        //printWriter.println("Connected.......\n");
                        printWriter.flush();
                        ID = Command.split("[|]")[1] + ".txt";
                        System.out.println(ID);
                        collectionName = ID;
                        collection = database.getCollection(collectionName);
                    }

                    if (Command.contains("SIMULATOR")) {

                        System.out.println("sim event running....");
                        counter++;
                        collectionName = Command.split("[|]")[1] ;
                        System.out.println("collection name set to....."+collectionName);
                        collection = database.getCollection(collectionName);
                        FindIterable cursor = collection.find().sort(new Document("_id",-1)).limit(4);
                        Iterator<Document> itr = cursor.iterator();
                        while(itr.hasNext()){
                            Document doc = itr.next();
                            System.out.println(counter);
                            printWriter.println(doc);
                            printWriter.flush();
                        }



                    }


                    if (Type.contains("MSH")) {
                        Document doc = new Document();
                        Scanner sc = new Scanner(bufferedReader);
                        doc.append("Header",Command);
                        StringBuilder sb = new StringBuilder();
                        while(sc.hasNextLine()){
                            String string = sc.nextLine();
                            sb.append(string+"\n");
                            if(string.contains("\u001C")){
                                break;
                            }

                            //System.out.println(sb.toString());
                        }
                        System.out.println(sb.toString());




                        Scanner scanner = new Scanner(sb.toString().replace("\u001C","eoloelo|itsOver"));
                        while(scanner.hasNext()){
                            String s = scanner.nextLine();
                            System.out.println(s);
                            String[] s1 = s.split("[|]");
                            if(!s.contains("eoloelo")){
                                doc.append(s1[0]+"|"+s1[1],s);
                            }if(s.contains("eoloelo")){
                                collection.insertOne(doc);
                                System.out.println("done!");
                                break;
                            }
                        }
                    }
                }else{
                    System.out.println("flying away.....");
                }



                } catch (IOException e) {
                e.printStackTrace();
            }



        }
    }}
