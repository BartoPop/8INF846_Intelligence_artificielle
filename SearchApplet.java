/*
    An applet which demonstrates 4 basic search algorithms

 Constructing Intelligent Agents with Java
   (C) Joseph P.Bigus and Jennifer Bigus 1997

 Copyright (c) 1998 John Wiley & Sons, Inc.  All rights reserved.  
 Reproduction or translation of this work beyond that permitted in 
 Section 117 of the 1976 United States Copyright Act without the 
 express written permission of the copyright owner is unlawful.  
 Requests for further information should be addressed to Permissions 
 Department, John Wiley & Sons, Inc. The purchaser may make back-up 
 copies for his/her own use only and not for distribution or resale.
 The Publisher assumes no responsibility for errors, omissions, or 
 damages, caused by the use of these programs or from the use of the 
 information contained herein.

*/

import java.awt.*;
import java.applet.*;
import java.util.* ;

public class SearchApplet extends Applet {

	void button1_Clicked(Event event) { // start
	   int method = 0 ;
	   SearchNode answer = null ;
	   SearchNode startNode ;
	   String start = choice1.getSelectedItem() ;
	   startNode = (SearchNode)graph.get(start) ;
	   String goal = choice2.getSelectedItem() ;
	   graph.reset() ;  // reset all nodes for another search
	   if (radioButton1.getState() == true) {
        textArea1.appendText("\n\nDepth-First Search for " + goal + ":\n\n");
	    answer = graph.depthFirstSearch(startNode,goal) ;
	   } // endif
	   if (radioButton2.getState() == true){
	    textArea1.appendText("\n\nBreadth-First Search for " + goal + ":\n\n");
	    answer = graph.breadthFirstSearch(startNode, goal) ;
       } // endif
       if (radioButton3.getState() == true) {
        textArea1.appendText("\n\nIterated-Deepening Search for " + goal + ":\n\n");
        answer = graph.iterDeepSearch(startNode, goal) ; //
       } // endif
       if (radioButton4.getState() == true) {
        textArea1.appendText("\n\nBest-First Search for " + goal + ":\n\n");
        choice2.select("Rochester") ; // goal must be Rochester
        answer = graph.bestFirstSearch(startNode, "Rochester") ;
       } // endif

       if (answer == null) {
         textArea1.appendText("Could not find answer!\n");
       } else {
         textArea1.appendText("Answer found in node " + answer.label);
       }
   	}

    void button2_Clicked(Event event) { // stop
       // for later use --- when we have large search problems
	}

	void button3_Clicked(Event event) {  // clear

		//{{CONNECTION
		// Clear the text for TextArea
		textArea1.setText("");
		//}}
	}

   // build a test graph and then call the specified search routine
   // this graph is a set of cites in the mid-west United States
  public static SearchGraph testGraph() {

        SearchGraph graph = new SearchGraph("test") ;
        // first build the example tree
	    SearchNode roch = new SearchNode("Rochester","Rochester");
	    graph.put(roch) ;
	    SearchNode sfalls = new SearchNode("Sioux Falls","Sioux Falls") ;
	    graph.put(sfalls) ;
	    SearchNode mpls = new SearchNode("Minneapolis","Minneapolis") ;
	    graph.put(mpls) ;
	    SearchNode lacrosse = new SearchNode("LaCrosse","LaCrosse") ;
	    graph.put(lacrosse) ;
	    SearchNode fargo = new SearchNode("Fargo","Fargo") ;
	    graph.put(fargo) ;
	    SearchNode stcloud = new SearchNode("St.Cloud","St.Cloud") ;
	    graph.put(stcloud) ;
	    SearchNode duluth = new SearchNode("Duluth","Duluth") ;
	    graph.put(duluth) ;
	    SearchNode wausau = new SearchNode("Wausau","Wausau") ;
	    graph.put(wausau) ;
	    SearchNode gforks = new SearchNode("Grand Forks","Grand Forks") ;
	    graph.put(gforks) ;
	    SearchNode bemidji = new SearchNode("Bemidji","Bemidji") ;
	    graph.put(bemidji) ;
	    SearchNode ifalls = new SearchNode("International Falls","International Falls") ;
	    graph.put(ifalls) ;
	    SearchNode gbay = new SearchNode("Green Bay","Green Bay") ;
	    graph.put(gbay) ;
	    SearchNode madison = new SearchNode("Madison","Madison") ;
	    graph.put(madison) ;
	    SearchNode dubuque = new SearchNode("Dubuque","Dubuque") ;
	    graph.put(dubuque) ;
	    SearchNode rockford = new SearchNode("Rockford","Rockford") ;
	    graph.put(rockford) ;
	    SearchNode chicago = new SearchNode("Chicago","Chicago") ;
	    graph.put(chicago) ;
	    SearchNode milwaukee = new SearchNode("Milwaukee","Milwaukee") ;
	    graph.put(milwaukee) ;

	    roch.addLinks(mpls, lacrosse, sfalls, dubuque) ;
	    mpls.addLinks(duluth, stcloud, wausau);
	    mpls.addLinks(lacrosse, roch ) ;
	    lacrosse.addLinks(madison, dubuque, roch);
	    lacrosse.addLinks(mpls,gbay) ;
	    sfalls.addLinks(fargo, roch) ;
	    fargo.addLinks(sfalls, gforks, stcloud) ;
	    gforks.addLinks(bemidji, fargo, ifalls) ;
	    bemidji.addLinks(gforks, ifalls, stcloud, duluth) ;
	    ifalls.addLinks(bemidji, duluth, gforks) ;
	    duluth.addLinks(ifalls,mpls, bemidji) ;
	    stcloud.addLinks(bemidji, mpls, fargo) ;
	    dubuque.addLinks(lacrosse, rockford) ;
	    rockford.addLinks(dubuque, madison, chicago) ;
	    chicago.addLinks(rockford, milwaukee) ;
	    milwaukee.addLinks(gbay, chicago) ;
        gbay.addLinks(wausau, milwaukee, lacrosse) ;
        wausau.addLinks(mpls, gbay) ;

	    // use as costs for best first search example
	    // straight line distances from cities to Rochester
        roch.cost = 0 ;      // goal
        sfalls.cost = 232 ;
        mpls.cost = 90 ;
        lacrosse.cost = 70 ;
        dubuque.cost = 140 ;
        madison.cost = 170 ;
        milwaukee.cost = 230 ;
        rockford.cost = 210 ;
        chicago.cost = 280 ;
        stcloud.cost = 140 ;
        duluth.cost = 180 ;
        bemidji.cost = 260 ;
        wausau.cost = 200 ;
        gbay.cost = 220;
        fargo.cost = 280;
        gforks.cost = 340;

	    return graph ;

  }




	public void init() {
		super.init();

		//{{INIT_CONTROLS
		setLayout(null);
		addNotify();
		resize(459,407);
		button1 = new java.awt.Button("Start");
		button1.reshape(36,360,100,30);
		add(button1);
		button2 = new java.awt.Button("Stop");
		button2.reshape(168,360,100,30);
		add(button2);
		button3 = new java.awt.Button("Clear");
		button3.reshape(312,360,100,30);
		add(button3);
 		Group1 = new CheckboxGroup();
		radioButton1 = new java.awt.Checkbox("Depth-First", Group1, false);
		radioButton1.reshape(24,300,100,24);
                 add(radioButton1) ; 
		radioButton2 = new java.awt.Checkbox("Breadth-First", Group1, false);
		radioButton2.reshape(24,324,120,23);
                 add(radioButton2) ; 
		radioButton3 = new java.awt.Checkbox("Iterative Deepening", Group1, false);
		radioButton3.reshape(216,300,168,25);
                 add(radioButton3) ; 
		radioButton4 = new java.awt.Checkbox("Best-First", Group1, false);
		radioButton4.reshape(216,324,122,25);
                 add(radioButton4) ; 
		choice2 = new java.awt.Choice();
		add(choice2);
		choice2.reshape(240,264,156,24);
		choice1 = new java.awt.Choice();
		add(choice1);
		choice1.reshape(36,264,144,24);
		label2 = new java.awt.Label("Goal State");
		label2.reshape(228,240,108,24);
		add(label2);
		label1 = new java.awt.Label("Start Node");
		label1.reshape(24,240,96,24);
		add(label1);
		textArea1 = new java.awt.TextArea();
		textArea1.reshape(0,0,456,240);
		add(textArea1);
		//}}

        // set up for search test applet
		graph = testGraph();              // build the test graph
		Enumeration enum = graph.keys() ; // get city names
		while(enum.hasMoreElements()) {
		    String name = (String)enum.nextElement();
		    choice1.addItem(name);   // fill start cities
		    choice2.addItem(name);   // fill goal cities
		} // endwhile
        radioButton1.setState(true) ;      // default to depth-first
        SearchNode.setDisplay(textArea1) ; // used for trace display
        choice1.select("Rochester") ;
	}

	public boolean handleEvent(Event event) {
		if (event.target == button1 && event.id == Event.ACTION_EVENT) {
			button1_Clicked(event);
			return true;
		}if (event.target == button2 && event.id == Event.ACTION_EVENT) {
			button2_Clicked(event);
			return true;
		}if (event.target == button3 && event.id == Event.ACTION_EVENT) {
			button3_Clicked(event);
			return true;
		}
		return super.handleEvent(event);
	}

	//{{DECLARE_CONTROLS
	java.awt.Button button1;
	java.awt.Button button2;
	java.awt.Button button3; 
	java.awt.Checkbox radioButton1;
	CheckboxGroup Group1;
	java.awt.Checkbox radioButton2;
	java.awt.Checkbox radioButton3;
	java.awt.Checkbox radioButton4;
	java.awt.Choice choice2;
	java.awt.Choice choice1;
	java.awt.Label label2;
	java.awt.Label label1;
	java.awt.TextArea textArea1;
	//}}
	SearchGraph graph ;
}
