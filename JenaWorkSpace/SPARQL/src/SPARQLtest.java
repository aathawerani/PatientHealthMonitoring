import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.iri.impl.Main;

import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;


public class SPARQLtest {

	/**
	 * @param args
	 */
	//online Tutorial available in the below link.
	//https://www.youtube.com/watch?v=nUdHneViLp4
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        Model m=FileManager.get().loadModel("file:///C:/Users/qrajput/ontologies/foaf.rdf");
        
       //---------------------------------------------------
      //              Query1
      //--------------------------------------------------
         String QueryStr="PREFIX dc: <http://purl.org/dc/elements/1.1/>" +"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
        	"PREFIX R:<http://xmlns.com/foaf/0.1/>"+
        		"SELECT ?subject ?Obj"+
        			"WHERE { ?subject a  R:Person. }";
        //---------------------------------------------------
        //              Query2
        //--------------------------------------------------
         /* String QueryStr="PREFIX R:<http://www.semanticweb.org/qrajput/ontologies/2014/3/untitled-ontology-14#>"+
        			"SELECT ?subject ?object"+
	                      "WHERE { ?subject R:name ?object. }";*/
        //----------------------------------------------------
        //              Query3
        //--------------------------------------------------
         /*String QueryStr="PREFIX R:<http://www.semanticweb.org/qrajput/ontologies/2014/3/untitled-ontology-14#>"+
    			"SELECT ?subject ?object"+
                      "WHERE { ?subject R:name ?object." +
    			       "Filter(?object=\"Sana\")"+
                      " }";*/
        //----------------------------------------------------
        //              Query4
        //--------------------------------------------------
           /*    String QueryStr="PREFIX R:<http://www.semanticweb.org/qrajput/ontologies/2014/3/untitled-ontology-14#>"+
    			"SELECT *"+
                      "WHERE { ?p1 R:Knows ?p2." +
    			        "?p1 R:name ?x ."+
    			        "?p2 R:name ?y ."+
                      "Filter(?y=\"Kashif\")"+
    			        " }";*/
        //----------------------------------------------------
        Query query = QueryFactory.create(QueryStr);
        
        QueryExecution qexec=QueryExecutionFactory.create(query,m);
        
        try{
        	ResultSet result=qexec.execSelect();
        	
        	while(result.hasNext()){
        		QuerySolution Sol=result.nextSolution();
        		//Literal n=Sol.getLiteral("p1");  // Query4
        	    Resource n=Sol.getResource("subject");  // Query 1 2 3 
        		System.out.println(n);
        		
        	}
        }
        finally{
        	qexec.close();
        }
	}

}
