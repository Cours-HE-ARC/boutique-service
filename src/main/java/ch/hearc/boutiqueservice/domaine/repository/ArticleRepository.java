package ch.hearc.boutiqueservice.domaine.repository;

import ch.hearc.boutiqueservice.domaine.model.Article;
import ch.hearc.boutiqueservice.domaine.model.Biere;

public interface ArticleRepository {

	Article getArticleByNoArticle(String noArticle);
}
