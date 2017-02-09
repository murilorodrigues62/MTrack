// Generated code from Butter Knife. Do not modify!
package br.com.rodrigues.murilo.mtrack.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ViewSamplesActivity$$ViewBinder<T extends br.com.rodrigues.murilo.mtrack.ui.ViewSamplesActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558525, "method 'onFabClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onFabClicked(p0);
        }
      });
  }

  @Override public void unbind(T target) {
  }
}
