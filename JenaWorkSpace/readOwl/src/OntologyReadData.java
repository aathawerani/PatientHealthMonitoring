


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.ontology.AllValuesFromRestriction;
import com.hp.hpl.jena.ontology.BooleanClassDescription;
import com.hp.hpl.jena.ontology.DataRange;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.MaxCardinalityRestriction;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.Restriction;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.shared.PrefixMapping;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class OntologyReadData {
	
	public void ShowClassDescription(PrintStream out, OntModel m){
		 // ShowOntologyClass(out,m);
		//ShowObjectProperties(out, m);
		//ShowDataProperties(out,m);
		//ShowRestriction(out,m);
		//ShowIndividual(out, m);
		inferedModel(out,m);
		
			}
    public void inferedModel(PrintStream out, OntModel m){
    	
    	String NS="http://www.semanticweb.org/qrajput/ontologies/2014/3/untitled-ontology-14#";
	    	Individual p1 = m.getIndividual( NS + "p1" );
	    	for (Iterator<Resource> i = p1.listRDFTypes(true); i.hasNext(); ) {
	    	    System.out.println( p1.getURI() + " is inferred to be in class " + i.next() );
	    	}
	        
	        
	       
	    
    }
	public void ShowOntologyClass(PrintStream out, OntModel m)
	{    OntClass cls=null;
	     int depth=0;
		 Iterator<OntClass> i = m.listHierarchyRootClasses();
		
		 while(i.hasNext())
		 {
		  cls=(OntClass)i.next(); 
		  if(cls.getLocalName()!=null)
		  ShowClasses(out,cls,new ArrayList(), depth);
	    }
		  
		 
	 }
	
	
	public void ShowClasses(PrintStream out, OntClass str,List occur, int depth)
	{
		for(int d=0;d<depth;d++)
			out.print("-");
		     //if(str.getLocalName()!=null)
		     {
	    	    out.println(str.getLocalName());//.getLocalName());
	}
			 for (Iterator i = str.listSubClasses( true );  i.hasNext(); ) {
	                OntClass str1 = (OntClass) i.next();
                 
	                occur.add( str1 );
	                ShowClasses( out, str1, occur, depth + 1 );
	                occur.remove( str1 );
	         }
     }
		
		
	public void ShowObjectProperties(PrintStream out, OntModel m){
	
		Iterator<ObjectProperty> i=m.listObjectProperties();
		out.println("\n\nList of Object Properties \n -------------------------------------");
		while(i.hasNext()){
			ObjectProperty p=(ObjectProperty)i.next();
			  out.println(p.getLocalName()+"  Domain= "+ p.getDomain().getLocalName()+"  Range= "+p.getRange().getLocalName());
			
			}
		
	}
	
	public void ShowDataProperties(PrintStream out, OntModel m){
		
		Iterator<DatatypeProperty> i=m.listDatatypeProperties();
		out.println("\n\nList of Data Properties \n -------------------------------------");
		while(i.hasNext()){
			DatatypeProperty p=(DatatypeProperty)i.next();
			 out.println(p.getLocalName()+"  Domain= "+ p.getDomain().getLocalName()+"  Range= "+p.getRange().getLocalName());
			 
			}
		
	}
	public void ShowIndividual(PrintStream out, OntModel m){
		Iterator<Individual> itI = m.listIndividuals();

	    while (itI.hasNext()) {
	        Individual i = itI.next();
	        System.out.println(i.getLocalName());
	    }
	}
	
	public void ShowRestriction(PrintStream out, OntModel m)
	{
		
		 for (Iterator i = m.listClasses();  i.hasNext(); ) 
		 {
			 OntClass cls=(OntClass)i.next();
			 
			System.out.println("\n"+cls.getLocalName());
			 if(cls.isRestriction())
			 { 
				 
				 Restriction r=cls.asRestriction();
				 out.println("\n\n**on Property " + r.getOnProperty());//.getLocalName());
				
			        out.println();

			       if (r.isMaxCardinalityRestriction()){
			    	   out.print(" Maximum Cardinality ");
			            out.print( r.asMaxCardinalityRestriction().getMaxCardinality() );
			       }
			       
			       else if(r.isMinCardinalityRestriction()){
			    	   out.print("Minimum Cardinality ");
			            out.print( r.asMinCardinalityRestriction().getMinCardinality() );
			       }
			       
			       else if (r.isAllValuesFromRestriction()) {
			         // out.print("all values from  ");
			          AllValuesFromRestriction av = r.asAllValuesFromRestriction();
			          System.out.println( "AllValuesFrom class " +
	                          av.getAllValuesFrom().getLocalName() +
	                          " on property " + av.getOnProperty().getLocalName() );
			          //out.print(r.asAllValuesFromRestriction().getAllValuesFrom().getLocalName());
			           
			        }
			       else if (r.isSomeValuesFromRestriction()) {
			           out.print("Some values from  ");
			           out.print(r.asSomeValuesFromRestriction().getSomeValuesFrom().getLocalName());
			            
			        }
			       else if (r.isHasValueRestriction()) {
			           out.print("\n\n has value ");
			           
			        	RDFNode value= r.asHasValueRestriction().getHasValue();
			        	if (value instanceof Resource) {
			                Resource res = (Resource) value;
			              {	
			                	out.print(res.getModel().shortForm(res.getURI()));
			                  
			              }
			            }
			            else if (value instanceof Literal) {
			                out.print( ((Literal) value).getLexicalForm() );
			            }
			            else {
			                out.print( value );
			            }
			        }
			 }
		 }
	
	}
	
	   
  }//class
