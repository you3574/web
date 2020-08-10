package net.skhu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.VO.Answer;
import net.skhu.VO.Article;
import net.skhu.mapper.AnswerMapper;
import net.skhu.mapper.ArticleMapper;
import net.skhu.model.AnswerModel;
import net.skhu.model.ArticleModel;


@Service
public class QnAService {

	@Autowired ArticleMapper articleMapper;
	@Autowired AnswerMapper answerMapper;

    public Article getOne(int id) {
        return articleMapper.getOne(id);
    }

    public List<Article> getMyArticles(int id) {
        return articleMapper.getMyArticles(id);
    }

    public List<Article> getAllArticles() {

        return articleMapper.getAllArticles();
    }

    public void update(ArticleModel a) {
        articleMapper.update(a.getId(),a.getSubject(),a.getMessage());
    }

    public void updateYN(boolean yes,int id) {
    	articleMapper.updateYN(yes,id);
    }

    public void insert(ArticleModel a, int writerId) {
        Article last = articleMapper.findTopOrderByNoDesc();
        int no = (last == null) ? 1 : last.getNo() + 1;

        Article article = new Article();

        article.setNo(no);
        article.setWriterId(writerId);
        article.setTime(new Date());
        article.setSubject(a.getSubject());
        article.setMessage(a.getMessage());

        articleMapper.insert(article);

    }

    public void delete(int id) {
        articleMapper.delete(id);
    }

	public Answer getAnswer(int id) {

		return answerMapper.getAnswer(id);
	}

	public void insertAnswer(AnswerModel a,int articleId,String adminId) {

		Answer answer=new Answer();

		answer.setArticleId(articleId);
		answer.setAdminId(adminId);
		answer.setMessage(a.getMessage());
		answer.setTime(new Date());

		answerMapper.insert(answer);


	}
	public void insertNotice(ArticleModel a, String adminId) {
		 Article last = articleMapper.findTopOrderByNoDesc();
	        int no = (last == null) ? 1 : last.getNo() + 1;

	        Article article = new Article();
	        int writerId= Integer.parseInt(adminId);

	        article.setNo(no);
	        article.setWriterId(writerId);
	        article.setTime(new Date());
	        article.setSubject(a.getSubject());
	        article.setMessage(a.getMessage());
	        article.setNotice(true);

	        articleMapper.insertNotice(article);


	}


	public void updateAnswer(String message,int articleId) {

		answerMapper.update(message,articleId);
	}



}
