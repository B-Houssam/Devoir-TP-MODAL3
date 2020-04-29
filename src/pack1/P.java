package pack1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class P {

	public P() {
		// TODO Auto-generated constructor stub
	}
	

	public static void main(String[] args) {
		try {
			//
			//trigger exception
			//int kk = 14/0;
			//
			
			JFileChooser fi = new JFileChooser();
		    fi.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		    fi.showOpenDialog(null);
		     
		    String path = fi.getSelectedFile().toString();
			
			File file = new File(path);
			String project = file.getName();
			File packs = new File(path+"/src/");
			

			String[] directories = packs.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});
			int nbPacks = directories.length;
			//System.out.println(Arrays.toString(directories));
			
			
			//pour recuperer le nombre total des classes dans tout les packages
			List<String> lc = new ArrayList<String>();
			for (int i = 0; i < nbPacks; i++) {
				File cls = new File(packs+"/"+directories[i]+"/");
				String[] classes = cls.list(new FilenameFilter() {
					  @Override
					  public boolean accept(File current, String name) {
					    return new File(current, name).isFile();
					  }
					});
				for (int j = 0; j < classes.length; j++) {
					lc.add(classes[j]);
				}
			}	
			
			/*
			
			*/
					
			int count=1;
			
			for (int j = 0; j < nbPacks; j++) {
				
				List<String> lcc = new ArrayList<String>();
			
					File cls = new File(packs+"/"+directories[j]+"/");
					String[] classes = cls.list(new FilenameFilter() {
						  @Override
						  public boolean accept(File current, String name) {
						    return new File(current, name).isFile();
						  }
						});
					for (int jj = 0; jj < classes.length; jj++) {
						lcc.add(classes[jj]);
					}
				
				
				
				for (int i = 0; i <lcc.size() ; i++) {
					
					File f =new File(System.getProperty("user.dir") + "/Results" + count + ".xml");
					BufferedWriter bw = new BufferedWriter(new FileWriter(f,false));
					
					File ff = new File(path+"/bin/");
					URL[] cp = {ff.toURI().toURL()};
					URLClassLoader urlcl = new URLClassLoader(cp);
					Class c = urlcl.loadClass(directories[j]+"."+lcc.get(i).substring(0, lcc.get(i).lastIndexOf('.')));
					
					
					bw.write("<DateTime>");
					bw.newLine();
					bw.write(new Date().toString());
					bw.newLine();
					bw.write("</DateTime>");
					bw.newLine();
					bw.write("<ProjectName");
					bw.newLine();
					bw.write(""+project);
					bw.newLine();
					bw.write("</ProjectName>");
					bw.newLine();
					bw.write("<JavaVersion>");
					bw.newLine();
					bw.write(System.getProperty("java.version"));
					bw.newLine();
					bw.write("</JavaVersion>");
					bw.newLine();
					bw.write("<OS>");
					bw.newLine();
					bw.write(System.getProperty("os.name"));
					bw.newLine();
					bw.write("</OS>");
					bw.newLine();
					bw.write("<Class>");
					bw.newLine();
					bw.write("<ClassName>");
					bw.newLine();
					bw.write(c.getSimpleName());
					bw.newLine();
					bw.write("</ClassName>");
					bw.newLine();
					bw.write("<PackageName>");
					bw.newLine();
					bw.write(c.getPackage().getName());
					bw.newLine();
					bw.write("</PackageName>");
					bw.newLine();
					bw.write("<nbAttributsPubliques>");
					bw.newLine();
					bw.write(""+c.getFields().length);
					bw.newLine();
					bw.write("</nbAttributsPubliques>");
					bw.newLine();
					bw.write("<NomsTypesAttributs>");
					bw.newLine();
					
					Field[] fields = c.getFields();
					for (Field oneField : fields) {
						Field field = c.getField(oneField.getName());
						bw.write("<Attribut>");
						bw.newLine();
						bw.write("<NomAttribut>");
						bw.newLine();
						bw.write(field.getName());
						bw.newLine();
						bw.write("</NomAttribut>");
						bw.newLine();
						bw.write("<TypeAttribut>");
						bw.newLine();
						bw.write(field.getType().getSimpleName());
						bw.newLine();
						bw.write("</TypeAttribut>");
						bw.newLine();
						bw.write("</Attribut>");
						bw.newLine();
					}
					
					bw.write("</NomsTypesAttributs>");
					bw.newLine();
					bw.write("<nbAttributsPrivés>");
					bw.newLine();
					bw.write(""+(c.getDeclaredFields().length - c.getFields().length));
					bw.newLine();
					bw.write("</nbAttributsPrivés>");
					bw.newLine();
					bw.write("<NomsTypesAttributsPrivés>");
					bw.newLine();
					
					List<Field> pl = new ArrayList<Field>(); 
					Field[] pFields = c.getDeclaredFields();
					Field[] ppFields = c.getFields();
					for (Field oneField : pFields) {
						pl.add(oneField);
					}
					for (Field oneField : ppFields) {
						pl.remove(oneField);
					}
					for (int k = 0; k < pl.size(); k++) {
						Field privateField = pl.get(k);
						bw.write("<AttributPrivé>");
						bw.newLine();
						bw.write("<NomAttribut>");
						bw.newLine();
						bw.write(privateField.getName());
						bw.newLine();
						bw.write("</NomAttribut>");
						bw.newLine();
						bw.write("<TypeAttribut>");
						bw.newLine();
						bw.write(privateField.getType().getSimpleName());
						bw.newLine();
						bw.write("</TypeAttribut>");
						bw.newLine();
						bw.write("</AttributPrivé>");
						bw.newLine();
					}
					
					bw.write("</NomsTypesAttributsPrivés>");
					bw.newLine();
					
					
					
					
					bw.write("</Class>");
					
								
					/*
				
				
							
					
				
					br.write("Liste des constructeurs: [ ");
					Constructor[] constructor = c.getDeclaredConstructors();
					for (Constructor oneCon : constructor) {
						br.write(oneCon.getName() + " ");
					}
					br.write("]");
					br.newLine();
					Method[] declaredMethodes = c.getDeclaredMethods();
					br.write("Nombre de méthodes: " + declaredMethodes.length);
					br.newLine();
					br.write("Liste des méthodes: [ ");
					for (Method meth : declaredMethodes) {
						br.write(meth.getName() + ", ");
					}
					br.write("]");
					br.newLine();
					br.write("Type de retour des méthodes: [ ");
					for (Method meth : declaredMethodes) {
						br.write(meth.getName() + ": " + meth.getReturnType().getSimpleName() +", ");
					}
					br.write("]");
					br.newLine();
					br.write("Paramètres et types des méthodes: ");
					for (Method meth : declaredMethodes) {
						br.write(meth.getName()+"[");
						Class[] parameterTypes = meth.getParameterTypes();
						for (int k = 0; k < parameterTypes.length; k++) {
							br.write("param" + (k+1) + ": " + parameterTypes[k].getSimpleName() +", ");
						}
						br.write("], ");
					}
					br.newLine();
					br.write("-------------------------------------------------------------------------------------------------------------------------------------");	
					*/				
					count++;
					bw.close();
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.toString());
			ExceptionSave s = new ExceptionSave();
			s.saveXml(e);
		} finally {
			JOptionPane.showMessageDialog(null, "Please check Source folder for exceptions or Results");
			System.out.println("\n\n->Job Done!");
		}
	}
}
