package jp.ac.shibaura.it.ie.domain.application.category;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.model.category.Category;
import jp.ac.shibaura.it.ie.domain.model.category.CategoryRepository;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecases.category.list.CategoryListOutputData;
import jp.ac.shibaura.it.ie.usecases.category.list.CategoryListInputData;
import jp.ac.shibaura.it.ie.usecases.category.list.CategoryListUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryListInteractor implements CategoryListUseCase {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LogUtils logger;

    @Override
    public CategoryListOutputData handle(CategoryListInputData inputData) {
        List<Category> tmp = categoryRepository.findAll();
        for (int i = 0; i < tmp.size(); i++) {
            logger.info("category/List:" + tmp.get(i).getCategoryName() + ":" + tmp.get(i).getCategoryId());
        }
        return new CategoryListOutputData(categoryRepository.findAll());
    }
}