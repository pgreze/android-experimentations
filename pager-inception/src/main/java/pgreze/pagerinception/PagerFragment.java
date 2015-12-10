package pgreze.pagerinception;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/** An activity's view page with another sub view pager */
public class PagerFragment extends Fragment implements PageFragment.PageContext {

    private int[] colors = new int[] {
            Color.GREEN,
            Color.CYAN,
            Color.RED,
            Color.YELLOW,
            Color.BLUE,
    };

    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_view_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.pager);

        // Note: without our PagerAdapter, if we're not using getChildFragmentManager, there are rendering issues
        // See http://stackoverflow.com/a/12090317/4899362
        adapter = new PagerFragmentAdapter(getChildFragmentManager());

        viewPager.setAdapter(adapter);

        // Add tabs when toolbar is clicked
        view.findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PagerInceptionActivity) getActivity()).appendViews();
            }
        });
    }

    @Override
    public void previous(Fragment fragment) {
        slideTo(-1);
    }

    @Override
    public void next(Fragment fragment) {
        slideTo(+1);
    }

    private void slideTo(int move) {
        int position = viewPager.getCurrentItem() + move;
        PageFragment.PageContext context = (PageFragment.PageContext) getActivity();

        if (position >= adapter.getCount()) {
            context.next(this);
        } else if (position == -1) {
            context.previous(this);
        } else {
            viewPager.setCurrentItem(position, true);
        }
    }

    /**
     * Default pager adapter, where items can't move:
     * <ul>
     *     <li>{@link #getItem(int)} always create an object</li>
     *     <li>{@link #getItemId(int) getItemId(position)} -> position</li>
     *     <li>{@link #getItemPosition(Object) getItemPosition(fragment)} return POSITION_UNCHANGED</li>
     * </ul>
     */
    class PagerFragmentAdapter extends FragmentPagerAdapter {

        public PagerFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return PageFragment.build("T" + i, colors[i]);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
