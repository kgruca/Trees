import java.util.*;
import java.text.*;

public class Practice {

	public static void main(String[] args) {
		FHtree<String> sceneTree = new FHtree<String>();
		FHtreeNode<String> tn;
		
		tn = sceneTree.addChild(null, "room");
		
		sceneTree.addChild(tn, "Lily the canine");
		sceneTree.addChild(tn, "Miguel the human");
		sceneTree.addChild(tn, "table");
		
		tn = sceneTree.find("Miguel the human");
		
		tn = sceneTree.addChild(tn, "torso");
		tn = sceneTree.addChild(tn, "left arm");
		tn = sceneTree.addChild(tn, "left hand");
		sceneTree.addChild(tn, "thumb");
		sceneTree.addChild(tn, "index finger");
		sceneTree.addChild(tn, "middle finger");
		sceneTree.addChild(tn, "ring finger");
		sceneTree.addChild(tn, "pinky");
		
		tn = sceneTree.find("Miguel the human");
		tn = sceneTree.find("torso");
		tn = sceneTree.addChild(tn, "right arm");
		tn = sceneTree.addChild(tn, "right hand");
		sceneTree.addChild(tn, "thumb");
		sceneTree.addChild(tn, "index finger");
		sceneTree.addChild(tn, "middle finger");
		sceneTree.addChild(tn, "ring finger");
		sceneTree.addChild(tn, "pinky");
		
		tn = sceneTree.find("Lily the canine");
		tn = sceneTree.addChild(tn, "torso");
		sceneTree.addChild(tn, "right front paw");
		sceneTree.addChild(tn, "left front paw");
		sceneTree.addChild(tn, "right rear paw");
		sceneTree.addChild(tn, "left rear paw");
		sceneTree.addChild(tn, "spare mutant paw");
		
		tn = sceneTree.find("table");
		sceneTree.addChild(tn, "northeast leg");
		sceneTree.addChild(tn,  "northwest leg");
		sceneTree.addChild(tn, "southeast leg");
		sceneTree.addChild(tn, "southwest leg");
		
		sceneTree.display();
		
		sceneTree.remove("spare mutant paw");
		sceneTree.remove("Miguel the human");
		sceneTree.remove("an imagined higg's boson");
		
		sceneTree.display();
	}
}
