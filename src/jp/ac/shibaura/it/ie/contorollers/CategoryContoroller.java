package jp.ac.shibaura.it.ie.contorollers;

import com.google.inject.Inject;
import jp.ac.shibaura.it.ie.domain.application.category.CategoryJoinInteractor;
import jp.ac.shibaura.it.ie.domain.application.category.CategoryListInteractor;
import jp.ac.shibaura.it.ie.usecases.category.join.CategoryJoinInputData;
import jp.ac.shibaura.it.ie.usecases.category.list.CategoryListInputData;
import jp.ac.shibaura.it.ie.usecases.core.OutputData;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@EnableAutoConfiguration
@RestController
@RequestMapping("/category")
public class CategoryContoroller {

    @Inject
    private CategoryListInteractor categoryListInteractor;
    @Inject
    private CategoryJoinInteractor categoryJoinInteractor;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public OutputData categoryList(@RequestHeader("session") String session, @RequestBody CategoryListInputData inputData) {
        return categoryListInteractor.handle(inputData);
    }

    @RequestMapping(value = "/{categoryId}/join", method = RequestMethod.GET)
    public OutputData categoryJoin(@RequestHeader("session") String session, @RequestBody CategoryJoinInputData inputData, @PathVariable("categoryId") String categoryId) {
        return categoryJoinInteractor.handle(inputData);
    }
}