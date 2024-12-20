import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.QuerySolutionMap;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;
import com.hp.hpl.jena.vocabulary.ResultSet;


public class CameraRead {

	/**
	 * @param args
	 */
	//https://jena.apache.org/documentation/ontology/
	public static void main(String[] args) {
		
		String url="file:///C:/Users/qrajput/ontologies/Per.rdf";
		// if you want inference
		OntModel m=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF,null);
		
		// not want inference
		//OntModel m=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,null);
		m.read(url);
		OntologyReadData R=new OntologyReadData();
		R.ShowClassDescription(System.out, m);
		}
}
