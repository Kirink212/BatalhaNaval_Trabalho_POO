public class SelectionController extends MainController{
	private static SelectionController s = null;
	
	public SelectionController()
	{
		
	}
	
	public static SelectionController getSelectionController(){
		if(s == null)
		{
			s = new SelectionController();
		}
	   
		return s;
	}
}
