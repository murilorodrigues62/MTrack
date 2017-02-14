// Generated code from Butter Knife. Do not modify!
package br.com.rodrigues.murilo.mtrack.ui.quote;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ArticleDetailFragment$$ViewBinder<T extends br.com.rodrigues.murilo.mtrack.ui.quote.ArticleDetailFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558528, "field 'quote'");
    target.quote = finder.castView(view, 2131558528, "field 'quote'");
    view = finder.findRequiredView(source, 2131558527, "field 'author'");
    target.author = finder.castView(view, 2131558527, "field 'author'");
    view = finder.findRequiredView(source, 2131558525, "field 'backdropImg'");
    target.backdropImg = finder.castView(view, 2131558525, "field 'backdropImg'");
    view = finder.findRequiredView(source, 2131558524, "field 'collapsingToolbar'");
    target.collapsingToolbar = finder.castView(view, 2131558524, "field 'collapsingToolbar'");
  }

  @Override public void unbind(T target) {
    target.quote = null;
    target.author = null;
    target.backdropImg = null;
    target.collapsingToolbar = null;
  }
}
