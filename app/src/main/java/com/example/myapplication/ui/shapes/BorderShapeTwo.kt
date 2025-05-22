package com.example.myapplication.ui.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class BorderShapeTwo: Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(Path().apply {
            moveTo(10f, 10f)
            lineTo(size.width + 20f, 20f)
            lineTo(size.width, size.height)
            lineTo(-10f, 260f)
            close()
        })
    }
}
