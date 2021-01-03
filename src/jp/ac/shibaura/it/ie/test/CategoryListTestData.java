package jp.ac.shibaura.it.ie.test;

import jp.ac.shibaura.it.ie.domain.model.category.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryListTestData {
    public List<CategoryData> categoryList = new ArrayList<>();

    public class CategoryData {
        private String categoryId;
        private String categoryName;
        public CategoryData(String categoryId, String categoryName){
            this.categoryId = categoryId;
            this.categoryName = categoryName;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }
    }
}
