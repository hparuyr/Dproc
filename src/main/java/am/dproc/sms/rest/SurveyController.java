package am.dproc.sms.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import am.dproc.sms.models.SurveyQuestion;
import am.dproc.sms.models.SurveyResult;
import am.dproc.sms.services.interfaces.SurveyQuestionService;
import am.dproc.sms.services.interfaces.SurveyResultService;
import io.swagger.annotations.Api;

@RestController
@Path("/survey")
@Api(value = "SurveyController")
public class SurveyController {

	@Autowired
	SurveyQuestionService surveyQuestionService;
	
	@Autowired
	SurveyResultService surveyResultService;

	@Autowired
	RestTemplate restTemplate;
	
	@Bean(initMethod = "initSurvey")
	public void initSurvey() throws IOException {
		if(getSurvey().size() > 0) {
			return;
		}
		ResponseEntity<String> response = getQuestionsPage();
		saveSurveyQuestions(response.getBody());
	}
	
	private int[] saveSurveyQuestions(String body) throws IOException{
		Document doc = Jsoup.parse(body);
		Elements newsHeadlines = doc.select("form table tbody tr");
		List<SurveyQuestion> surveyList = new ArrayList<>();
		for (Element headline : newsHeadlines) {
			String questionContent = headline.child(0).text();
			if(questionContent.length() < 1) {
				continue;
			}
		  if(headline.children().size() > 2) {
			  Element childData = headline.child(1);
			  if(childData.children().size() > 1) {
				  Element secondChildInput = headline.child(1).child(1);
				  SurveyQuestion survey = new SurveyQuestion();
				  survey.setContent(questionContent);
				  survey.setInputName(secondChildInput.attr("name"));
				  surveyList.add(survey);
			  }
		  }
		}
		return surveyQuestionService.createBatch(surveyList);
	}
	
	//gets all survey questions
	@GET
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<SurveyQuestion> getSurvey() {
		return surveyQuestionService.getAll();
	}

	private ResponseEntity<String> submitSurvey(Map<String, String> answers) {
		String host = "https://openpsychometrics.org";
		String submitUrl = "https://openpsychometrics.org/tests/IPIP-BFFM/2.php";

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("unqid", "5d70b53b1fe591078232897");
		params.add("seconds", Integer.toString((int)(System.currentTimeMillis()/1000)));
		
		for (Entry<String, String> answer : answers.entrySet()) {
			int randomSec = (int) (Math.random() * 1000 + 1000);
//			int randomVal = (int) (Math.random() * 5);
			params.add(answer.getKey()+"_E", String.valueOf(randomSec));
			params.add(answer.getKey(), answer.getValue());
		}
		params.add("onblurs", "0");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setCacheControl("max-age=0");
		headers.setOrigin(host);

		ResponseEntity<String> response = doPostRequest(submitUrl, params, headers);
		return response;
	}

	private ResponseEntity<String> getQuestionsPage() {
		String host = "https://openpsychometrics.org";
		String submitUrl = "https://openpsychometrics.org/tests/IPIP-BFFM/1.php";

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("unqid", "5d70b53b1fe591078232897");
		params.add("seconds", Integer.toString((int)(System.currentTimeMillis()/1000)));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setCacheControl("max-age=0");
		headers.setOrigin(host);

		ResponseEntity<String> response = doPostRequest(submitUrl, params, headers);
		return response;
	}
	
	private ResponseEntity<String> doPostRequest(String url, MultiValueMap<String, String> params, HttpHeaders headers){
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params,headers);
		return restTemplate.postForEntity(url, entity, String.class);
	}
	
	@Path("/result/{studentId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public SurveyResult getResultByStudentId(@PathParam("studentId") int studentId) {
		return surveyResultService.getByStudentId(studentId);
	}
	
	@Path("/result")
	@GET
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<SurveyResult> getAllResults() {
		return surveyResultService.getAll();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	public int updateGroupId(SurveyResult surveyResult) {
		return surveyResultService.update(surveyResult);
	}
	
	@Path("/{studentId}")
	@DELETE
	public int delete(@PathParam("studentId") int studentId) {
		return surveyResultService.delete(studentId);
	}

	@DELETE
	public int deleteAll() {
		return surveyResultService.deleteAll();
	}
	
	private SurveyResult getResponseScores(ResponseEntity<String> response) {
		Document doc = Jsoup.parse(response.getBody());
		
		SurveyResult surveyResult = new SurveyResult();
//		int randomStudentId = (int) (Math.random() * 1000);
		surveyResult.setStudentId(3);
		surveyResult.setExt(Float.parseFloat(doc.select("form input[type='hidden'][name='EXT']").first().attr("value")));
		surveyResult.setEst(Float.parseFloat(doc.select("form input[type='hidden'][name='EST']").first().attr("value")));
		surveyResult.setAgr(Float.parseFloat(doc.select("form input[type='hidden'][name='AGR']").first().attr("value")));
		surveyResult.setCsn(Float.parseFloat(doc.select("form input[type='hidden'][name='CSN']").first().attr("value")));
		surveyResult.setOpn(Float.parseFloat(doc.select("form input[type='hidden'][name='OPN']").first().attr("value")));
		return surveyResult;
	}

	@Path("/submit")
	@POST
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	public int submit(Map<String, String> answers) {
		ResponseEntity<String> response = submitSurvey(answers);
		SurveyResult surveyResult = getResponseScores(response);
		return surveyResultService.create(surveyResult);
	}
}
