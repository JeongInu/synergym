import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home"
import Login from "./pages/Login"
import Join from "./pages/Join"

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/join" element={<Join />} />
    </Routes>
  );
};

export default App;
