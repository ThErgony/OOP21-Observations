package oop21.tentelli.fonti.observations;

import java.io.IOException;

public interface FirstLoader {

	void firstLoad(final String dir, final String students, final String moments, final String types,
			final Saved save, final Loader loader) throws IOException;

}