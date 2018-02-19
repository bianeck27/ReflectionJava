import java.util.List;
import java.util.Map;

public class Principal {

	public static void main(String[] args) throws Exception {
		Mapeador m = new Mapeador();
		
		m.load("classes.prop");
		
		System.out.println(m.getImplementacao(List.class));
		System.out.println(m.getImplementacao(Map.class));

	}

}
