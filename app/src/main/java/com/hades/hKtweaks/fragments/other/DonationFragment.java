package com.hades.hKtweaks.fragments.other;

import com.hades.hKtweaks.R;
import com.hades.hKtweaks.fragments.recyclerview.RecyclerViewFragment;
import com.hades.hKtweaks.utils.Utils;
import com.hades.hKtweaks.views.recyclerview.DescriptionView;
import com.hades.hKtweaks.views.recyclerview.ImageView;
import com.hades.hKtweaks.views.recyclerview.RecyclerViewItem;
import com.hades.hKtweaks.views.recyclerview.CardView;

import java.util.List;
import java.util.Objects;

/**
 * Created by Morogoku on 05/12/2017.
 */

public class DonationFragment extends RecyclerViewFragment {


    @Override
    protected boolean showViewPager() {
        return false;
    }

    @Override
    protected void addItems(List<RecyclerViewItem> items) {

        CardView donate = new CardView(getActivity());
        donate.setTitle(getString(R.string.donation_title));
        donate.setFullSpan(true);

        DescriptionView desc1 = new DescriptionView();
        desc1.setSummary(getString(R.string.donation_summary));
        desc1.setOnItemClickListener(item
                -> Utils.launchUrl("https://www.paypal.me/corsicanu", Objects.requireNonNull(getActivity())));
        donate.addItem(desc1);

        ImageView img1 = new ImageView();
            img1.setDrawable(getResources().getDrawable(R.drawable.ic_paypal));
        img1.setOnItemClickListener(item
                -> Utils.launchUrl("https://www.paypal.me/corsicanu", Objects.requireNonNull(getActivity())));
        donate.addItem(img1);
        items.add(donate);

    }
}
