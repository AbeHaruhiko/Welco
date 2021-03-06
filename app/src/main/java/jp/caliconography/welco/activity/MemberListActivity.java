package jp.caliconography.welco.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import jp.caliconography.welco.R;
import jp.caliconography.welco.fragment.MemberListFragment;
import jp.caliconography.welco.model.parseobject.Member;
import jp.caliconography.welco.service.SlackClient;

public class MemberListActivity extends FragmentActivity
        implements MemberListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Callback method from {@link MemberListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(Member selectedMember) {
        if (mTwoPane) {
//            // In two-pane mode, show the detail view in this activity by
//            // adding or replacing the detail fragment using a
//            // fragment transaction.
//            Bundle arguments = new Bundle();
//            arguments.putString(MemberDetailFragment.ARG_ITEM_ID, id);
//            MemberDetailFragment fragment = new MemberDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.member_detail_container, fragment)
//                    .commit();

        } else {
            new SlackClient().sendMessage(selectedMember.getSlackPath(),
                    new SlackClient.SlackMessage(getString(R.string.slack_msg_guest_has_come),
                            getString(R.string.app_name), ":gohst:"));

        }
    }
}
