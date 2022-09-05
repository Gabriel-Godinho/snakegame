
package br.univille;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Application")
public class ApplicationTest {

  @Test
  @DisplayName("Pointless test")
  void smokeTest() {
      assertThat(true).isEqualTo(true);
  }

  @Test
  @DisplayName("Points")
  void pointsTest() {
      assertEquals(true, GameSettings.getScore() == 0);
  }
}
