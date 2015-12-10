package pgreze.pagerinception;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class PagerInceptionActivity extends AppCompatActivity implements PageFragment.PageContext {
    private static final String TAG = PagerInceptionActivity.class.getName();

    private PagerInceptionActivityAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        viewPager = (ViewPager) findViewById(R.id.main_pager);

        adapter = new PagerInceptionActivityAdapter(getSupportFragmentManager(), savedInstanceState);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        adapter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    public void appendViews() {
        if (adapter.getCount() != 3) {
            adapter.expand();
        }

        // Go to the second tab
        viewPager.setCurrentItem(1, true);
    }

    @Override
    public void previous(Fragment fragment) {
        slideTo(viewPager.getCurrentItem() - 1);
    }

    @Override
    public void next(Fragment fragment) {
        slideTo(viewPager.getCurrentItem() + 1);
    }

    private void slideTo(int position) {
        if (position >= 0 && position < adapter.getCount()) {
            viewPager.setCurrentItem(position, true);
        }
    }

    public static final String FRAGMENTS_KEY = "FRAGMENTS_KEY";

    /**
     * Pager adapter with transition from 1 to 3 fragments.<br/>
     * It's working because:
     * <ul>
     *     <li>Our moving tab is the unique PagerFragment in ViewPager</li>
     *     <li>We're knowing this PagerFragment will move from index 0 to 2</li>
     * </ul>
     * <br/>
     * Sadly a generic algorithm is hard because:
     * <ul>
     *     <li>Fragments are recreated at each restart -> impossible to match id -> fragment</li>
     *     <li>Fragments tag are not public</li>
     * </ul>
     */
    class PagerInceptionActivityAdapter extends FragmentPagerAdapter {

        protected int count = 1;

        public PagerInceptionActivityAdapter(FragmentManager fm, Bundle savedInstanceState) {
            super(fm);
            if (savedInstanceState != null) {
                count = savedInstanceState.getInt(FRAGMENTS_KEY);
            }
        }

        public void onSaveInstanceState(Bundle outState) {
            outState.putInt(FRAGMENTS_KEY, count);
        }

        @Override
        public long getItemId(int position) {
            // PagerFragment will be the first page
            // 0th and 1st will have an item id > 0
            return count == 1 || position == 2 ? 0 : position + 1;
        }

        @Override
        public int getItemPosition(Object object) {
            // Npte: ViewPager doesn't care if you're returning
            // already known position or POSITION_UNCHANGED
            if (object instanceof PagerFragment) {
                return count == 1 ? 0 : 2;
            } else {
                // Other tabs will never move
                return POSITION_UNCHANGED;
            }
        }

        public void expand() {
            // Update internal count
            count = 3;
            // And notify view pager
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            // Note: this method have to create a new instance
            // I would prefer createItem(position)...
            Log.i(TAG, "Get item at " + position);
            if (count == 1 || position == 2) {
                return new PagerFragment();
            } else {
                return PageFragment.build(
                        String.format("%02d", position),
                        position == 0 ? Color.BLACK : Color.RED);
            }
        }

        @Override
        public int getCount() {
            return count;
        }
    }
}
