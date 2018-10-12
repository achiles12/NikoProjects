package company.citymanager.helpers;

public class DBWorldQueries {
	
	public static String getCitiesByDistrictByPopulation()
	{
		return "select * " + 
			   "from city " +
			   "where CountryCode = 'PHL' " +
			   "order by Population DESC, District ASC";
	}
	
	public static String getCountriesByName()
	{
		return "select Code," +
				     " Name," +
				     " Population " +
			   "from country " + 
			   "order by Name";
	}

}
