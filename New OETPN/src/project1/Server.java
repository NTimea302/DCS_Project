package project1;

import java.util.ArrayList;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFloat;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Server {
	
	public static void main(String[] args) {
		
		PetriNet pn = new PetriNet();
		pn.PetriNetName = "Server Petri";
		pn.NetworkPort = 1081;
		
		DataFloat subConstantValue1 = new DataFloat();
		subConstantValue1.SetName("subConstantValue1");
		subConstantValue1.SetValue(0.01f);
		pn.ConstantPlaceList.add(subConstantValue1);
		
		// PLACES
		DataFloat p0 = new DataFloat();
		p0.SetName("P0");
		p0.SetValue(1.0f);
		pn.PlaceList.add(p0);
	
		DataFloat p1 = new DataFloat();
		p1.SetName("P1");
		//p1.SetValue(1.0f);
		pn.PlaceList.add(p1);
		
		DataFloat p2 = new DataFloat();
		p2.SetName("P2");
		//p2.SetValue(2.0f);
		pn.PlaceList.add(p2);

		DataTransfer p3 = new DataTransfer();
		p3.SetName("P3");
		p3.Value = new TransferOperation("localhost", "1082", "P5");
		pn.PlaceList.add(p3);
		
		
		// TRANSITIONS
		
		//T1
		PetriTransition t1 = new PetriTransition(pn);
		t1.TransitionName = "T1";
		t1.InputPlaceName.add("P0");
		t1.InputPlaceName.add("P1");
		
		Condition T1Ct1 = new Condition(t1, "P0", TransitionCondition.NotNull);
		Condition T1Ct2 = new Condition(t1, "P1", TransitionCondition.NotNull);
		T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);
		ArrayList<String> lstInput = new ArrayList<String>();
		lstInput.add("P1");
		lstInput.add("subConstantValue1");
		
		GuardMapping grdT1 = new GuardMapping();
		grdT1.condition= T1Ct1;
		grdT1.Activations.add(new Activation(t1, lstInput, TransitionOperation.Prod, "P2"));
		t1.GuardMappingList.add(grdT1);
		
		pn.Transitions.add(t1);
		
		// T2
		PetriTransition t2 = new PetriTransition(pn);
		t2.TransitionName = "T2";
		t2.InputPlaceName.add("P2");
		
		Condition T2Ct1 = new Condition(t2, "P2", TransitionCondition.NotNull);

		GuardMapping grdT2 = new GuardMapping();
		grdT2.condition= T2Ct1;
		grdT2.Activations.add(new Activation(t2, "P2", TransitionOperation.Move, "P0"));
		grdT2.Activations.add(new Activation(t2, "P2", TransitionOperation.SendOverNetwork, "P3"));
		t1.GuardMappingList.add(grdT2);
		
		pn.Transitions.add(t2);
		
		System.out.println("Server started \n ------------------------------");
		pn.Delay = 3000;
		//pn.Start();
		
		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = pn;
		frame.setVisible(true);
	}
}
