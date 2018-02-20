import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Mapeador {

	private Map<Class<?>, Class<?>> mapa = new HashMap<>();

	public void load(String nomeArquivo) throws Exception {
		Properties p = new Properties();
		p.load(new FileInputStream(nomeArquivo));
		for(Object key : p.keySet()) {
			Class<?> interf = Class.forName(key.toString());
			Class<?> implem = Class.forName(p.get(key).toString());
			
			if (!interf.isInterface()) {
				throw new RuntimeException("O tipo " + interf.getName() + " não é uma interface");
			}
			
			if (!interf.isAssignableFrom(implem)) {
				throw new RuntimeException("A classe " + implem.getName() 
				+ " não implementa " + interf.getName());
			}
			mapa.put(interf, implem);
		}
	}
	
	public Class<?> getImplementacao(Class<?> interf){
		return mapa.get(interf);
	}
	
	@SuppressWarnings("deprecation")
	public Object getInstancia(Class<?> interf) throws Exception {
		Class<?> impl = mapa.get(interf);
		return impl.newInstance();
	}
	
}
