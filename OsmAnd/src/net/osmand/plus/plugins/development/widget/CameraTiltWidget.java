package net.osmand.plus.plugins.development.widget;

import static net.osmand.plus.views.mapwidgets.WidgetType.DEV_CAMERA_TILT;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.osmand.plus.activities.MapActivity;
import net.osmand.plus.views.OsmandMapTileView;
import net.osmand.plus.views.layers.base.OsmandMapLayer.DrawSettings;
import net.osmand.plus.views.mapwidgets.WidgetType;
import net.osmand.plus.views.mapwidgets.widgets.TextInfoWidget;

public class CameraTiltWidget extends TextInfoWidget {


	private final OsmandMapTileView mapView;
	private double cachedMapTilt;

	public CameraTiltWidget(@NonNull MapActivity mapActivity) {
		super(mapActivity, WidgetType.DEV_CAMERA_TILT);
		this.mapView = mapActivity.getMapView();
		updateInfo(null);
		setIcons(DEV_CAMERA_TILT);
	}

	@Override
	public void updateInfo(@Nullable DrawSettings drawSettings) {
		double mapTilt = mapView.getElevationAngle();
		if (isUpdateNeeded() || Math.abs(mapTilt - cachedMapTilt) > 0.05d) {
			cachedMapTilt = mapTilt;
			setText(String.format("%.1f °", cachedMapTilt ), null);
		}
	}
}