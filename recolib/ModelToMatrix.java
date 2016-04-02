package recolib;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.model.DataModel;

public class ModelToMatrix {

	static int items_size = 0;
	static int users_size = 0;

	public static double[][] init(DataModel model) throws TasteException {
		double[][] data = new double[6045][4000];

		LongPrimitiveIterator us_it = model.getUserIDs();
		LongPrimitiveIterator it_it = model.getItemIDs();

		int[] items = new int[6100];

		// items_size = 0;
		// users_size=0;

		while (it_it.hasNext()) {
			items[items_size++] = safeLongToInt(it_it.next());
		}

		// int x = 1;

		while (us_it.hasNext()) {

			int user_id = safeLongToInt(us_it.next());

			for (int i = 0; i < items_size; i++) {
				// System.out.print(user_id + " & " + items[i] + ": ");
				if (model.getPreferenceValue(user_id, items[i]) == null) {
					data[user_id][items[i]] = 0;
					// System.out.println(x++ + ": " + '0');
				} else {
					data[user_id][items[i]] = model.getPreferenceValue(user_id, items[i]);
					// System.out.println(x++ + ": " +
					// model.getPreferenceValue(user_id, items[i]) );
				}

				// data[user_id][(int) items[i]] =
				// (model.getPreferenceValue(user_id, (long)
				// items[i])==null)?0:model.getPreferenceValue(user_id, (long)
				// items[i]);
				// System.out.println(x++ + ":
				// "+model.getPreferenceValue(user_id, (long) items[i])==
				// null?0:model.getPreferenceValue(user_id, (long) items[i]));
			}
			users_size++;

		}

		/*
		 * for(int i=0;i<users_size;i++) { for(int j=0;j<items_size;j++) {
		 * System.out.print( " " + data[i][items[j]]); } System.out.println("");
		 * }
		 */

		return data;
	}

	public static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
		}
		return (int) l;
	}

}
