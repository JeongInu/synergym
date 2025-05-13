import Section1 from "../components/sections/Section1";
import AxiosTest from "./AxiosTest";
import Section2 from "../components/sections/Section2";
import Section3 from "../components/sections/Section3";
import Section4 from "../components/sections/Section4";
import Section5 from "../components/sections/Section5";

const Home = () => {
  return (
    <>
      <Section1 />
      <AxiosTest />
      <Section2 />
      <Section3 />
      <Section4 />
      <Section5 />
    </>
  );
};

export default Home;