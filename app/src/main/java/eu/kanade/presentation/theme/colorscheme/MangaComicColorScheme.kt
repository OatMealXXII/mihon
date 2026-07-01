package eu.kanade.presentation.theme.colorscheme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * Colors for Manga Comic theme
 * Inspired by Japanese anime/manga comic book aesthetics.
 */
internal object MangaComicColorScheme : BaseColorScheme() {

    override val darkScheme = darkColorScheme(
        primary = Color(0xFF00BEFF),
        onPrimary = Color(0xFF000000),
        primaryContainer = Color(0xFF00384E),
        onPrimaryContainer = Color(0xFFE6F7FF),
        inversePrimary = Color(0xFF00668B),
        secondary = Color(0xFF00BEFF),
        onSecondary = Color(0xFF000000),
        secondaryContainer = Color(0xFF00384E),
        onSecondaryContainer = Color(0xFFE6F7FF),
        tertiary = Color(0xFF39C5BB),
        onTertiary = Color(0xFF000000),
        tertiaryContainer = Color(0xFF003C38),
        onTertiaryContainer = Color(0xFFE6FFFD),
        background = Color(0xFF121212),
        onBackground = Color(0xFFFFFFFF),
        surface = Color(0xFF1E1E1E),
        onSurface = Color(0xFFFFFFFF),
        surfaceVariant = Color(0xFF2D2D2D),
        onSurfaceVariant = Color(0xFFFFFFFF),
        surfaceTint = Color(0xFF00BEFF),
        inverseSurface = Color(0xFFFFFFFF),
        inverseOnSurface = Color(0xFF000000),
        outline = Color(0xFFFFFFFF),
        surfaceContainerLowest = Color(0xFF141414),
        surfaceContainerLow = Color(0xFF1C1C1C),
        surfaceContainer = Color(0xFF262626),
        surfaceContainerHigh = Color(0xFF303030),
        surfaceContainerHighest = Color(0xFF3B3B3B),
    )

    override val lightScheme = lightColorScheme(
        primary = Color(0xFF00BEFF),         // Hatsune Miku Cyan / Sky Blue
        onPrimary = Color(0xFF000000),       // Solid Black text
        primaryContainer = Color(0xFFE6F7FF), // Soft pastel cyan-tinted white
        onPrimaryContainer = Color(0xFF000000),
        inversePrimary = Color(0xFF39C5BB),
        secondary = Color(0xFF00BEFF),
        onSecondary = Color(0xFF000000),
        secondaryContainer = Color(0xFFE6F7FF),
        onSecondaryContainer = Color(0xFF000000),
        tertiary = Color(0xFF39C5BB),
        onTertiary = Color(0xFF000000),
        tertiaryContainer = Color(0xFFE6FFFD),
        onTertiaryContainer = Color(0xFF000000),
        background = Color(0xFFF5F7FA),       // Crisp anime-style white / light soft gray
        onBackground = Color(0xFF000000),     // Solid Black
        surface = Color(0xFFFFFFFF),          // Pure White
        onSurface = Color(0xFF000000),        // Solid Black
        surfaceVariant = Color(0xFFE6F7FF),   // Soft pastel cyan-tinted white
        onSurfaceVariant = Color(0xFF000000), // Solid Black
        surfaceTint = Color(0xFF00BEFF),
        inverseSurface = Color(0xFF000000),
        inverseOnSurface = Color(0xFFFFFFFF),
        outline = Color(0xFF000000),          // Solid Black border outline
        surfaceContainerLowest = Color(0xFFFFFFFF),
        surfaceContainerLow = Color(0xFFF5F7FA),
        surfaceContainer = Color(0xFFE6F7FF),
        surfaceContainerHigh = Color(0xFFE6F7FF),
        surfaceContainerHighest = Color(0xFFE6F7FF),
    )
}
