using Xunit;
using ESTeSoccer.Controllers;
using Microsoft.AspNetCore.Mvc;

namespace ESTeSoccerTest
{
    public class HomeControllerTest
    {
        [Fact]
        public void Privacy_ReturnsViewResult()
        {
            //Arrange
            var controller = new HomeController(null);

            //Act
            var result = controller.Privacy();

            // Assert
            var viewResult = Assert.IsType<ViewResult>(result);
            Assert.NotNull(viewResult);
        }
        [Fact]
        public void Index_ReturnsViewResult()
        {
            //Arrange
            var controller = new HomeController(null);

            //Act
            var result = controller.Index();

            // Assert
            var viewResult = Assert.IsType<ViewResult>(result);
            Assert.NotNull(viewResult);
        }
    }
}