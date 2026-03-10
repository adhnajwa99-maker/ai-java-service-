import React, { useState } from "react";
import { FaCode, FaBook, FaTools, FaLock, FaBolt } from "react-icons/fa";

const roadmap = [
  {
    phase: "Phase 0 – Planning & Prep",
    duration: "Now – 2 weeks",
    color: "bg-orange-400",
    features: [
      { icon: <FaBook />, text: "Define project goals: AI Assistant for GitHub developers" },
      { icon: <FaBook />, text: "Study competitors: GitHub Copilot, SonarQube" },
      { icon: <FaTools />, text: "Draw architecture diagram" },
      { icon: <FaBolt />, text: "Create Landing Page + waitlist" },
    ],
    goal: "Gather early users & feedback",
  },
  {
    phase: "Phase 1 – MVP",
    duration: "1–2 months",
    color: "bg-green-400",
    features: [
      { icon: <FaCode />, text: "Code snippet generation" },
      { icon: <FaBook />, text: "Documentation generation" },
      { icon: <FaBook />, text: "Code explanation" },
      { icon: <FaTools />, text: "Simple Web interface" },
      { icon: <FaCode />, text: "GitHub integration: small repos" },
      { icon: <FaLock />, text: "API authentication (basic)" },
    ],
    goal: "Launch first working version + gather feedback",
  },
  {
    phase: "Phase 2 – Smart Developer Assistant",
    duration: "1–2 months",
    color: "bg-blue-400",
    features: [
      { icon: <FaTools />, text: "Code analysis & debugging suggestions" },
      { icon: <FaTools />, text: "Refactoring suggestions" },
      { icon: <FaBolt />, text: "Auto-fix proposals (manual approval)" },
      { icon: <FaCode />, text: "Highlight important code parts" },
      { icon: <FaCode />, text: "Optional: VS Code plugin integration" },
    ],
    goal: "Strong developer experience + retention",
  },
  {
    phase: "Phase 3 – Project Intelligence",
    duration: "1–2 months",
    color: "bg-purple-400",
    features: [
      { icon: <FaCode />, text: "Full repository analysis (medium projects)" },
      { icon: <FaBolt />, text: "Architecture suggestions" },
      { icon: <FaBolt />, text: "Performance optimization" },
      { icon: <FaLock />, text: "Security checks" },
      { icon: <FaTools />, text: "Unit test generation" },
    ],
    goal: "Product differentiation & attract early adopters / companies",
  },
  {
    phase: "Phase 4 – SaaS Platform & Growth",
    duration: "Post-Summer",
    color: "bg-red-400",
    features: [
      { icon: <FaBolt />, text: "Dashboard: usage stats, API keys" },
      { icon: <FaLock />, text: "Subscription plans: free / paid" },
      { icon: <FaBolt />, text: "Referral system" },
      { icon: <FaTools />, text: "Enterprise packages" },
      { icon: <FaCode />, text: "On-premise deployment" },
    ],
    goal: "Turn project into monetizable SaaS + scale",
  },
];

export default function InteractiveRoadmap() {
  const [activePhase, setActivePhase] = useState(0);

  return (
    <div className="p-6 max-w-5xl mx-auto">
      <h1 className="text-3xl font-bold mb-8 text-center">
        AI GitHub Assistant Roadmap
      </h1>

      {/* Timeline Navigation */}
      <div className="flex justify-between mb-6 flex-wrap gap-2">
        {roadmap.map((phase, idx) => (
          <button
            key={idx}
            onClick={() => setActivePhase(idx)}
            className={`px-3 py-1 rounded-full font-semibold transition
              ${
                idx === activePhase
                  ? "bg-black text-white scale-110"
                  : "bg-gray-300 text-black hover:scale-105"
              }`}
          >
            {phase.phase}
          </button>
        ))}
      </div>

      {/* Progress Bar */}
      <div className="relative h-2 bg-gray-200 rounded mb-6">
        <div
          className="h-2 rounded bg-black transition-all duration-500"
          style={{ width: `${((activePhase + 1) / roadmap.length) * 100}%` }}
        ></div>
      </div>

      {/* Phase Details */}
      <div
        className={`p-6 rounded-lg shadow-lg ${roadmap[activePhase].color} transition-transform transform hover:scale-105`}
      >
        <h2 className="text-2xl font-bold mb-2">{roadmap[activePhase].phase}</h2>
        <p className="italic mb-3">{roadmap[activePhase].duration}</p>
        <ul className="list-disc list-inside space-y-1 mb-3">
          {roadmap[activePhase].features.map((feat, i) => (
            <li key={i} className="flex items-center gap-2">
              <span className="text-lg">{feat.icon}</span>
              {feat.text}
            </li>
          ))}
        </ul>
        <p className="font-semibold">Goal: {roadmap[activePhase].goal}</p>
        <button className="mt-4 px-4 py-2 bg-white text-black font-bold rounded hover:bg-gray-200">
          Join Waitlist
        </button>
      </div>
    </div>
  );
}
