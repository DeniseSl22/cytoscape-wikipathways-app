package org.wikipathways.cytoscapeapp.internal.io;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualStyleFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import java.awt.Color;

public class GpmlVizStyle {
  final VisualMappingManager vizMapMgr;
  final VisualStyle vizStyle;

  public GpmlVizStyle(final VisualStyleFactory vizStyleFactory, final VisualMappingManager vizMapMgr) {
    this.vizMapMgr = vizMapMgr;
    this.vizStyle = create(vizStyleFactory, vizMapMgr);
  }

  private static VisualStyle create(final VisualStyleFactory vizStyleFactory, final VisualMappingManager vizMapMgr) {
    final VisualStyle vizStyle = vizStyleFactory.createVisualStyle(vizMapMgr.getDefaultVisualStyle());
    vizStyle.setTitle("WikiPathways");
    vizStyle.setDefaultValue(BasicVisualLexicon.NODE_FILL_COLOR, Color.WHITE);
    vizStyle.setDefaultValue(BasicVisualLexicon.NODE_LABEL_COLOR, Color.BLACK);
    vizMapMgr.addVisualStyle(vizStyle);
    return vizStyle;
  }

  public void apply(final CyNetworkView view) {
    vizMapMgr.setVisualStyle(vizStyle, view);
  }
}
