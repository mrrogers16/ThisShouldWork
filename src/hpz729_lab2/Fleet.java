package hpz729_lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Fleet {
	private String name;

	private ArrayList<Starship> starShipList = new ArrayList<Starship>();

	public Fleet(String name) {
		this.setName(name);
	}

	public void loadStarships(String directoryName) throws Exception {
		String line = "";
		String seperator = ",";
		boolean firstLine = true;

		File folder = new File(directoryName);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			firstLine = true;
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".csv")) {
				try (BufferedReader buffer = new BufferedReader(new FileReader(listOfFiles[i]))) {
					while ((line = buffer.readLine()) != null) {
						String[] fields = line.split(seperator);

						if (firstLine) {
							firstLine = false;
							// System.out.println(fields.length);
							Starship starShip = new Starship(null, null, null);
							starShip.setName(fields[0]);
							starShip.setRegistry(fields[1]);
							starShip.setShipClass(fields[2]);
							starShipList.add(starShip);
							System.out.println(starShipList.toString());
						}
						

						else if(fields.length == 5){
							//System.out.println(fields.length);
							CrewMember crewMember = new CrewMember(null, null, null, null, null);
							crewMember.setName(fields[0]);
							crewMember.setRank(fields[1]);
							crewMember.setPosition(fields[2]);
							crewMember.setSpecies(fields[3]);
							crewMember.setAssignment(fields[4]);
							starShipList.get(starShipList.size() - 1).addCrewMember(crewMember);
						
						
						}
							// System.out.println(fields.length);
							if (fields.length == 4) {
								//System.out.println(fields.length);
								CrewMember crewMember = new CrewMember(null, null, null, null);
								crewMember.setName(fields[0]);
								crewMember.setRank(fields[1]);
								crewMember.setPosition(fields[2]);
								crewMember.setSpecies(fields[3]);
								starShipList.get(starShipList.size() - 1).addCrewMember(crewMember);
								//System.out.println(crewMember.toString());
							}

						}
					}
				}
			}
		}

	@Override
	public String toString() {
		String result = "";
		for(int i = 0; i < starShipList.size() - 1;i++)
		{
			result += starShipList.get(i).toString() + "\n";	
		}
		
		result += "------------\n" + "  " + getName() + "  \n" + "------------\n";
		result += getSizeOfFleet() + " size of fleet.\n";

		for (int i = 0; i < starShipList.size() - 1; i++) 
		{
			result += starShipList.get(i).toString();
		}
		return result;
	}

	public void addStarship(Starship s) {
		if (starShipList == null) {
			starShipList = new ArrayList<Starship>();
		} else {
			starShipList.add(s);
		}
	}

	public int getSizeOfFleet() {
		int counter = 0;

		for (int i = 0; i < starShipList.size(); i++) {
			counter += 1;
		}
		return counter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}