package com.fekracomputers.islamiclibrary.userNotes.adapters;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.fekracomputers.islamiclibrary.R;
import com.xwray.groupie.ExpandableGroup;
import com.xwray.groupie.ExpandableItem;

/**
 * Created by Mohammad on 19/11/2017.
 */

public class ExpandableHeaderItem extends HeaderItem implements ExpandableItem {
    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;
    private ExpandableGroup expandableGroup;

    public ExpandableHeaderItem(@StringRes int titleStringResId, @StringRes int subtitleResId) {
        super(titleStringResId, subtitleResId);
    }

    public ExpandableHeaderItem(@StringRes int titleStringResId) {
        super(titleStringResId, 0);
    }

    @Override
    public void bind(@NonNull HeaderViewHolder viewHeaderViewHolder, int position) {
        super.bind(viewHeaderViewHolder, position);
        // Initial icon state -- not animated.
        viewHeaderViewHolder.icon.setVisibility(View.VISIBLE);
        bindIcon(viewHeaderViewHolder);
        viewHeaderViewHolder.icon.setOnClickListener(view -> {
            expandableGroup.onToggleExpanded();
            animateIcon(viewHeaderViewHolder.icon, expandableGroup.isExpanded());
        });
    }

    private void animateIcon(ImageView icon, boolean expanded) {
        ObjectAnimator imageViewObjectAnimator;
        if (!expanded) { // rotate clockwise
            imageViewObjectAnimator = ObjectAnimator.ofFloat(icon,
                    View.ROTATION, ROTATED_POSITION, INITIAL_POSITION);

        } else { // rotate counterclockwise
            imageViewObjectAnimator = ObjectAnimator.ofFloat(icon,
                    View.ROTATION, INITIAL_POSITION, ROTATED_POSITION);
        }

        imageViewObjectAnimator.setDuration(400);
        imageViewObjectAnimator.setInterpolator(new DecelerateInterpolator());
        imageViewObjectAnimator.start();
    }

    private void bindIcon(HeaderViewHolder viewHeaderViewHolder) {
        viewHeaderViewHolder.icon.setVisibility(View.VISIBLE);
        viewHeaderViewHolder.icon.setImageResource(R.drawable.ic_toc_expand_holo_dark_30dp);
        if (expandableGroup.isExpanded()) {
            viewHeaderViewHolder.icon.setRotation(ROTATED_POSITION);
        } else {
            viewHeaderViewHolder.icon.setRotation(INITIAL_POSITION);
        }

    }


    @Override
    public void setExpandableGroup(@NonNull ExpandableGroup onToggleListener) {
        this.expandableGroup = onToggleListener;
    }
}
