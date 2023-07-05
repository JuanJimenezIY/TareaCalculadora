package com.funcional.main;

import com.funcional.lista.Lista;
import com.funcional.tree.BinTree;

public class MainEjercicio {
	

	public static <T> Lista<T> postOrder(BinTree<T> tree){
		if(tree==BinTree.Leaf) {
			return Lista.NIL;
		}else {
	
	
				 var lsLeft=postOrder(tree.left());
			
				 var lsRight=postOrder(tree.right());
		
				
				 var tmp=lsRight.append(tree.value());
				 return lsLeft.concat(tmp);
				 
	}
		
	}
	public static Double eval(BinTree<String> tree) {
		if(tree==BinTree.Leaf) {
			return null;
		}else {

		Double lsLeft=eval(tree.left());
	
		Double lsRight=eval(tree.right());
		
		String tmp =tree.value();
		if (tmp.equals("+")) {
			return lsLeft+lsRight;
		}
		else if (tmp.equals("-")) {
			return lsLeft-lsRight;
		}
		else if (tmp.equals("*")) {
			return lsLeft*lsRight;
		}
		else if (tmp.equals("/")) {
			return lsLeft/lsRight;
		}
		return Double.parseDouble(tmp) ;
		}
		
		
	}
	
	public static void main(String[] args) {
		


		var suma = "+";
		var resta = "-";
		var mult = "*";
		var division = "/";
		
		var n3= BinTree.of("3");
	    var n5= BinTree.of("5");
		var nodoMultiplicacion = BinTree.of(mult,n3,n5);
		
	    var n2= BinTree.of("2");
		var nodoDivision = BinTree.of(division,  nodoMultiplicacion,n2);
		
		var n8= BinTree.of("8");
	    var n4= BinTree.of("4");
		var nodoResta =BinTree.of(resta,  n8, n4);
						
		var nodoSuma = BinTree.of(suma, nodoDivision, nodoResta);
		
		var tree=nodoSuma;
		
		double resultado = eval(tree);
		
		var post5= postOrder(tree);
		
		System.out.println(tree);
		System.out.println(post5);
		System.out.printf("Resultado = %.1f",resultado);


		
		
	}

}
