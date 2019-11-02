package am.dproc.sms.services.interfaces;

import java.io.File;
import java.io.IOException;

public interface CSVExportService {
	
	File getCSVFile(Integer id, Integer schoolID) throws IOException;

}
