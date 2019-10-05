package am.dproc.sms.services.interfaces;

import java.io.File;
import java.io.IOException;

public interface CSVExportService {
	
	public File getCSVFile(Integer id) throws IOException;

}
