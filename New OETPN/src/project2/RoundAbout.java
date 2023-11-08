package project2;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class RoundAbout {
	
	public static void main(String[] args) {
		PetriNet pn = new PetriNet();
		pn.PetriNetName = "RoundAbout";
		pn.NetworkPort = 8123;
				
		DataCar p4 = new DataCar();
		p4.SetName("p4");
		pn.PlaceList.add(p4);
		
		DataCar p5 = new DataCar();
		p5.SetName("p5");
		pn.PlaceList.add(p5);
		
		DataCarQueue p1 = new DataCarQueue();
		p1.Value.Size = 3;
		p1.SetName("p1");
		pn.PlaceList.add(p1);
		
		DataCar p6 = new DataCar();
		p6.SetName("p6");
		pn.PlaceList.add(p6);
		
		DataCar p7 = new DataCar();
		p7.SetName("p7");
		pn.PlaceList.add(p7);
		
		DataCarQueue p2 = new DataCarQueue();
		p2.Value.Size = 3;
		p2.SetName("p2");
		pn.PlaceList.add(p2);
		
		DataCar p9 = new DataCar();
		p9.SetName("p9");
		pn.PlaceList.add(p9);
		
		DataCar p8 = new DataCar();
		p8.SetName("p8");
		pn.PlaceList.add(p8);
		
		DataCarQueue p3 = new DataCarQueue();
		p3.Value.Size = 3;
		p3.SetName("p3");
		pn.PlaceList.add(p3);
		
		// T1------------------------------------------------------------------------
		PetriTransition t1 = new PetriTransition(pn);
		t1.TransitionName = "t1";
		t1.InputPlaceName.add("p1");
		Condition T1Ct1 = new Condition(t1,"p1", TransitionCondition.HaveCarForMe);
		Condition T1Ct2 = new Condition(t1,"p2", TransitionCondition.CanAddCars);
		T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);
		GuardMapping grdT1 = new GuardMapping();
		grdT1.condition = T1Ct1;
		grdT1.Activations.add(new Activation(t1, "p1", TransitionOperation.PopElementWithTargetToQueue,"p2"));
		t1.GuardMappingList.add(grdT1);
		t1.Delay = 0;
		pn.Transitions.add(t1);
		
		
		// T2------------------------------------------------------------------------
		PetriTransition t2 = new PetriTransition(pn);
		t2.TransitionName = "t2";
		t2.InputPlaceName.add("p2");
		Condition T2Ct1 = new Condition(t2,"p2", TransitionCondition.HaveCarForMe);
		Condition T2Ct2 = new Condition(t2,"p3", TransitionCondition.CanAddCars);
		T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);
		GuardMapping grdT2 = new GuardMapping();
		grdT2.condition = T2Ct1;
		grdT2.Activations.add(new Activation(t2, "p2", TransitionOperation.PopElementWithTargetToQueue,"p3"));
		t2.GuardMappingList.add(grdT2);
		t2.Delay = 0;
		pn.Transitions.add(t2);
		
		// T3------------------------------------------------------------------------
		PetriTransition t3 = new PetriTransition(pn);
		t3.TransitionName = "t3";
		t3.InputPlaceName.add("p3");
		Condition T3Ct1 = new Condition(t3,"p3", TransitionCondition.HaveCarForMe);
		Condition T3Ct2 = new Condition(t3,"p1", TransitionCondition.CanAddCars);
		T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);
		GuardMapping grdT3 = new GuardMapping();
		grdT3.condition = T3Ct1;
		grdT3.Activations.add(new Activation(t3, "p3", TransitionOperation.PopElementWithTargetToQueue,"p1"));
		t1.GuardMappingList.add(grdT3);
		t1.Delay = 0;
		pn.Transitions.add(t3);
		
		// T4------------------------------------------------------------------------
		PetriTransition t4 = new PetriTransition(pn);
		t4.TransitionName = "t4";
		t4.InputPlaceName.add("p1");
		Condition T4Ct1 = new Condition(t4,"p1", TransitionCondition.HaveCarForMe);
		GuardMapping grdT4 = new GuardMapping();
		grdT4.condition = T4Ct1;
		grdT4.Activations.add(new Activation(t4, "p1", TransitionOperation.PopElementWithTarget,"p4"));
		t4.GuardMappingList.add(grdT4);
		t4.Delay = 0;
		pn.Transitions.add(t4);
		
		// T5------------------------------------------------------------------------
		PetriTransition t5 = new PetriTransition(pn);
		t5.TransitionName = "t5";
		t5.InputPlaceName.add("p5");
		Condition T5Ct1 = new Condition(t5,"p5", TransitionCondition.NotNull);
		Condition T5Ct2 = new Condition(t5,"p1", TransitionCondition.CanAddCars);
		T5Ct1.SetNextCondition(LogicConnector.AND, T5Ct2);
		GuardMapping grdT5 = new GuardMapping();
		grdT5.condition = T5Ct1;
		grdT5.Activations.add(new Activation(t5, "p5", TransitionOperation.AddElement,"p1"));
		t5.GuardMappingList.add(grdT5);
		t5.Delay = 0;
		pn.Transitions.add(t5);
		
		// T6------------------------------------------------------------------------
		PetriTransition t6 = new PetriTransition(pn);
		t6.TransitionName = "t6";
		t6.InputPlaceName.add("p6");
		Condition T6Ct1 = new Condition(t6,"p6", TransitionCondition.NotNull);
		Condition T6Ct2 = new Condition(t6,"p2", TransitionCondition.CanAddCars);
		T6Ct1.SetNextCondition(LogicConnector.AND, T6Ct2);
		GuardMapping grdT6 = new GuardMapping();
		grdT6.condition = T6Ct1;
		grdT6.Activations.add(new Activation(t6, "p6", TransitionOperation.AddElement,"p2"));
		t6.GuardMappingList.add(grdT6);
		t6.Delay = 0;
		pn.Transitions.add(t6);
		
		// T7------------------------------------------------------------------------
		PetriTransition t7 = new PetriTransition(pn);
		t7.TransitionName = "t7";
		t7.InputPlaceName.add("p2");
		Condition T7Ct1 = new Condition(t7,"p2", TransitionCondition.HaveCarForMe);
		GuardMapping grdT7 = new GuardMapping();
		grdT7.condition = T7Ct1;
		grdT7.Activations.add(new Activation(t7, "p2", TransitionOperation.PopElementWithTarget,"p7"));
		t7.GuardMappingList.add(grdT7);
		t7.Delay = 0;
		pn.Transitions.add(t7);
		
		// T8------------------------------------------------------------------------
		PetriTransition t8 = new PetriTransition(pn);
		t8.TransitionName = "t8";
		t8.InputPlaceName.add("p8");
		Condition T8Ct1 = new Condition(t8,"p8", TransitionCondition.NotNull);
		Condition T8Ct2 = new Condition(t8,"p3", TransitionCondition.CanAddCars);
		T8Ct1.SetNextCondition(LogicConnector.AND, T8Ct2);
		GuardMapping grdT8 = new GuardMapping();
		grdT8.condition = T8Ct1;
		grdT8.Activations.add(new Activation(t8, "p8", TransitionOperation.AddElement,"p3"));
		t8.GuardMappingList.add(grdT8);
		t8.Delay = 0;
		pn.Transitions.add(t8);
		
		// T9------------------------------------------------------------------------
		PetriTransition t9 = new PetriTransition(pn);
		t9.TransitionName = "t9";
		t9.InputPlaceName.add("p3");
		Condition T9Ct1 = new Condition(t9,"p3", TransitionCondition.HaveCarForMe);
		GuardMapping grdT9 = new GuardMapping();
		grdT9.condition = T9Ct1;
		grdT9.Activations.add(new Activation(t9, "p3", TransitionOperation.PopElementWithTarget,"p9"));
		t9.GuardMappingList.add(grdT9);
		t9.Delay = 0;
		pn.Transitions.add(t9);

		
		
		System.out.println("Round About started \n ------------------------------");
		pn.Delay = 2500;
		
		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = pn;
		frame.setVisible(true);
	}
}